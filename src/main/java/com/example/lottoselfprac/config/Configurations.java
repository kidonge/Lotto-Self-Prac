package com.example.lottoselfprac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class Configurations {

    /* Feel free to change the type of key and value in the Map
     * from String, String to anything of your choice
     */
    @Bean
    public HashMap<String, Integer> myMap(){
        HashMap<String, Integer> map = new HashMap<>();
        return map;
    }

    /*Your other bean exporting methods*/

}
