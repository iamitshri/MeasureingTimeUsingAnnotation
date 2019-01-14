package com.iamitshri.katas.service;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import com.iamitshri.katas.annotation.LoggerAnnotation;

@Component
public class SampleService {

    @LoggerAnnotation(value = "AnnotationValue")
    public boolean sampleMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return false;
    }
}
