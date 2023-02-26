package xyz.luyie.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  11:30
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ServerApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServerApplication.class,args);

    }
}
