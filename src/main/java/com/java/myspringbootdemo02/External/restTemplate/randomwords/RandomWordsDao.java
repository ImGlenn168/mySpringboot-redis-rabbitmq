package com.java.myspringbootdemo02.External.restTemplate.randomwords;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RandomWordsDao {
    @RequestMapping(value = "/getWords",method = RequestMethod.GET,
            produces = {"application/json;charset=utf-8"})
    String getWords();
}
