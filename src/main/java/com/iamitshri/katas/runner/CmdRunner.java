package com.iamitshri.katas.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.iamitshri.katas.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CmdRunner implements CommandLineRunner {


    @Autowired
    SampleService service;

    @Override
    public void run(String... args) throws Exception {

        service.sampleMethod();

    }


}
