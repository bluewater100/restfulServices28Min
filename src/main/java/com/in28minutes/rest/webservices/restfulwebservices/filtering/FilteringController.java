package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {

        return new SomeBean("Value1","Value2","Value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBeans() {

        return Arrays.asList(new SomeBean("Value1","Value2","Value3"),
                new SomeBean("Value11","Value22","Value33"));
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue retrieveSomeBean2() {

        SomeBean2 someBean2 = new SomeBean2("Value1","Value2","Value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean2);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans2() {

        List<SomeBean2> list = Arrays.asList(new SomeBean2("Value1","Value2","Value3"),
                new SomeBean2("Value11","Value22","Value33"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;

    }


}
