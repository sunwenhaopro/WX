package xyz.luyie.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.luyie.weather.service.WeatherService;
import xyz.luyie.weather.vo.ResponseResult;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  10:46
 *
 */
@RestController
@Slf4j
public class WeatherController {

    @Autowired
    WeatherService weatherService;
    /**
     *  1.请求第三方URL
     *  2.
     */


    //根据城市名称，返回天气信息（JSON格式的数据）
    @RequestMapping("/city/{cityName}")
    public ResponseResult getCityName(@PathVariable("cityName") String cityName){
        log.info("cityName="+cityName);
        ResponseResult responseResult = null;
        try {
            responseResult = weatherService.getCityName(cityName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseResult;
    }


}
