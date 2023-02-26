package xyz.luyie.weather.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xyz.luyie.weather.service.WeatherService;
import xyz.luyie.weather.vo.ResponseResult;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  11:19
 */
@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final static String URL="http://wthrcdn.etouch.cn/weather_mini?city=";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult getCityName(String cityName) {
        /**
         * 1.需要cityName
         * 2.需要网址 http://wthrcdn.etouch.cn/weather_mini?city=赣州
         * 3.请求 一般使用httpclient，springboot通过restTemplate 来请求
         * 4.获取请求的body
         */

        log.info("下面开始请求第三方");
        ResponseEntity<String>  responseEntity = restTemplate.getForEntity(URL + cityName, String.class);
        log.info("responseEntity="+responseEntity);

        //获取请求的body
        String responseEntityBody = responseEntity.getBody();
        //把 responseEntityBody 转成 ResponseResult，通过ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseResult responseResult= null;
        try {
            // objectMapper.readValue(待转数据,哪个类.class);
            responseResult = objectMapper.readValue(responseEntityBody, ResponseResult.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.info("转换出了异常");
        }
        return  responseResult;
    }
}
