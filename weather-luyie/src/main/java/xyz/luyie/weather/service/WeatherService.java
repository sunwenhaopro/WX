package xyz.luyie.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.luyie.weather.vo.ResponseResult;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  11:17
 */

public interface WeatherService {
    ResponseResult getCityName(String cityName) throws JsonProcessingException;
}
