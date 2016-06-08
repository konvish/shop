/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  AreasService.java 2016-01-18 15:55:22 $
 */

import com.kong.shop.domain.Areas;
import com.kong.shop.service.IAreasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-service.xml")
public class AreasServiceTest{
    @Autowired
    private IAreasService areasService;

    @Test
    public void test(){
        //showcase
        assertEquals(1, areasService.findAll().size());
    }

    @Test
    public void addArea() throws Exception {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("e://area.txt"), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String lineTxt = null;
        Integer tt = 0;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            String[] word = lineTxt.split("\t");
            Areas areas = new Areas();
            if (word.length == 1) {
                if (word[0].equals("")) continue;
                areas.setName(word[0]);
                areas.setParentId(-1);
                areas.setCode("00");
                tt = add(areas);
            } else if (word.length > 3) {
                areas.setName(word[0]);
                areas.setCode(word[2]);
                areas.setParentId(tt);
                add(areas);
                Areas a = new Areas();
                a.setName(word[3]);
                a.setCode(word[5]);
                a.setParentId(tt);
                add(a);
            }else {
                areas.setName(word[0]);
                areas.setCode(word[1]);
                areas.setParentId(-1);
                add(areas);
            }
        }
        isr.close();
    }

    private Integer add(Areas areas){
        int f = areasService.add(areas);
        Integer op = Integer.parseInt(String.valueOf(areas.getId()));
        return op;
    }
}
