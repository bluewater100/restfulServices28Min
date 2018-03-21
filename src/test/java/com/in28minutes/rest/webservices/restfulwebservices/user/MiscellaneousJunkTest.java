package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.in28minutes.rest.webservices.restfulwebservices.person.PersonController;


public class MiscellaneousJunkTest {
	
	private static final Logger logger = Logger.getLogger(PersonController.class);
	    
    @Test
    public void testStrings() throws Exception {
    	
	    	String x = null;

	    	if (StringUtils.isEmpty(x)) {
	        	logger.info("1 = x is empty");
	    	}
	    	
	    	if (StringUtils.isNotEmpty(x)) {
	        	logger.info("2 = x is not empty");
	    	}
	    	
	    	if (x == null) {
	        	logger.info("3 = x is null");
	    	}
    	
	    	String y = "";
	    	
	    	if (StringUtils.isEmpty(y)) {
	        	logger.info("2 = y is empty");
	    	}
	    	
	    	if (StringUtils.isNotEmpty(y)) {
	        	logger.info("3 = y is not empty");
	    	}
	    	
	    	String t = " ";
	    	
	    	if (StringUtils.isEmpty(t)) {
	        	logger.info("1 = t is empty");
	    	}
	    	
	    	if (StringUtils.isNotEmpty(t)) {
	        	logger.info("2 = t is not empty");
	    	}

    }
    
    @Test
    public void testLoops() throws Exception {
    	
    		List<String> list = new ArrayList<>();
    		list.add("One");
    		list.add("Two");
    		list.add("Three");

    		for (String str : list) {
	        	logger.info("str = " + str);
    			
    		}
    		
    		
    		HashMap<String, Object> hm = new HashMap<>();
    		hm.put("One", "HMStringOne");
    		hm.put("Two", "HMStringTwo");
    		hm.put("Three", "HMStringThree");
    		
    		
    		//  If only interested in keys
    		for (String key : hm.keySet()) {
	        	logger.info("hm key = " + key);    			
    		}
    		
    		// If you only need the values, use values():
    		for (Object value: hm.values()) {
	        	logger.info("hm value = " + value);
   		}

    		//if you want both the key and value, use entrySet():
    		for (Map.Entry<String, Object> entry : hm.entrySet()) {
    			String key = entry.getKey();
    		    Object value = entry.getValue();
	        	logger.info("hm key = " + key + "-----" + "hm value = " + value.toString());
    		    
    		}
    		
    		// OR 
    		Iterator<Map.Entry<String, Object>> it = hm.entrySet().iterator();
    	    while (it.hasNext()) {
    	        Map.Entry<String, Object> pair = it.next();
    	        System.out.println("iterator result = " + pair.getKey() + " = " + pair.getValue());
//    	        it.remove(); // avoids a ConcurrentModificationException
    	    }    		
    		
    }
    

}
