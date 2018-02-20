package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    // the @GetMapping replaces the RequestMapping  - either way works
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World4!!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {

        return new HelloWorldBean("Hello bean1");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    //  The locale can be passed in the request header.  However, it is not required and if
    // it isnt passed in we will use the default locale specified in the RestfulWebServicesApplication class
    // For some reason spring boot is not finding the message.properties files so currently this is not working.  The
    // code matches the udemy example.
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        return messageSource.getMessage("good.morning.message", null, locale);

    }


}
