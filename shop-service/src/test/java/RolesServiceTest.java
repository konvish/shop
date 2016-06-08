/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  RolesService.java 2016-01-18 15:55:27 $
 */

import com.kong.shop.service.IRolesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-service.xml")
public class RolesServiceTest{
    @Autowired
    private IRolesService rolesService;

    @Test
    public void test(){
        //showcase
        assertEquals(1, rolesService.findAll().size());
    }
}
