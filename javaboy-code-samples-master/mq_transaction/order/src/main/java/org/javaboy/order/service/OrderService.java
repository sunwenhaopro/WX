package org.javaboy.order.service;

import org.javaboy.order.dao.OrderRepository;
import org.javaboy.order.domain.Order;
import org.javaboy.service.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @RabbitListener(queues = "order:locked")
    public void handle(OrderDTO msg) {
        LOG.info("Get new order to create:{}", msg);
        if (orderRepository.findOneByUuid(msg.getUuid()) != null) {
            LOG.info("Msg already processed:{}", msg);
        } else {
            Order order = newOrder(msg);
            orderRepository.save(order);
            msg.setId(order.getId());
        }
        msg.setStatus("NEW");
        rabbitTemplate.convertAndSend("order:pay", msg);
    }

    private Order newOrder(OrderDTO dto) {
        Order order = new Order();
        order.setUuid(dto.getUuid());
        order.setAmount(dto.getAmount());
        order.setTitle(dto.getTitle());
        order.setCustomerId(dto.getCustomerId());
        order.setTicketNum(dto.getTicketNum());
        order.setStatus("NEW");
        order.setCreatedDate(ZonedDateTime.now());
        return order;
    }

    @Transactional
    @RabbitListener(queues = "order:finish")
    public void handleFinish(OrderDTO msg) {
        LOG.info("Get finished order:{}", msg);
        Order order = orderRepository.getById(msg.getId());
        order.setStatus("FINISH");
        orderRepository.save(order);
    }

    /**
     * ??????????????????????????????
     * 1. ????????????????????????
     * 2. ??????????????????????????????????????????
     * 3. ?????????????????????????????????
     * @param msg
     */
    @Transactional
    @RabbitListener(queues = "order:fail")
    public void handleFailed(OrderDTO msg) {
        LOG.info("Get failed order:{}", msg);
        Order order;
        if (msg.getId() == null) {
            order = newOrder(msg);
            order.setReason("TICKET_LOCK_FAIL"); // ????????????????????????id?????????????????????????????????
        } else {
            order = orderRepository.getById(msg.getId());
            if (msg.getStatus().equals("NOT_ENOUGH_DEPOSIT")) {
                order.setReason("NOT_ENOUGH_DEPOSIT");
            }
        }
        order.setStatus("FAIL");
        orderRepository.save(order);
    }

    @Scheduled(fixedDelay = 10000L)
    public void checkInvalidOrder() {
        ZonedDateTime checkTime = ZonedDateTime.now().minusMinutes(1L);
        List<Order> orders = orderRepository.findAllByStatusAndCreatedDateBefore("NEW", checkTime);
        orders.stream().forEach(order -> {
            LOG.error("Order timeout:{}", order);
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setTicketNum(order.getTicketNum());
            dto.setUuid(order.getUuid());
            dto.setAmount(order.getAmount());
            dto.setTitle(order.getTitle());
            dto.setCustomerId(order.getCustomerId());
            dto.setStatus("TIMEOUT");
            rabbitTemplate.convertAndSend("order:ticket_error", dto);
        });
    }
}
