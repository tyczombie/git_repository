package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class GetResource {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContext.class);
        Resource resource = annotationConfigApplicationContext.getResource("https://www.baidu.com");
        System.out.println(resource.contentLength());
        System.out.println(resource.getFilename());
    }
}
