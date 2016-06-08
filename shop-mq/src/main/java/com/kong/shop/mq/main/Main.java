package com.kong.shop.mq.main;

import cn.thinkjoy.dap.dataservice.MessageData;
import cn.thinkjoy.dap.dataservice.ResourceListener;
import cn.thinkjoy.kafka.DefaultKafkaConsumer;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kong on 2016/4/3 0003.
 */
public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static ApplicationContext ctx = null;
    private static Map<String,Object> map = null;

    public static void main(String[] args){
        new Main();
    }

    public Main() {
        initSpring();
        initKafka();
    }

    private void initSpring(){
        if (ctx == null){
            ctx = new ClassPathXmlApplicationContext("spring-mq.xml");
            logger.info("容器初始化成功");
        }
    }

    private void initKafka(){
        if (map == null){
            map = new HashMap<String, Object>();
            //装载消息体
            map.put("product","shop");
        }
        try {
            DefaultKafkaConsumer.getInstance(false).receive(map, new ResourceListener() {
                @Override
                public void onEvent(MessageData messageData) {
                    logger.info(messageData.getData());
                    JSONObject result = JSONObject.fromObject(messageData.getData());
                    //业务
                }
            });
        }catch (Exception e){
            logger.error("init failed",e);
        }
    }
}
