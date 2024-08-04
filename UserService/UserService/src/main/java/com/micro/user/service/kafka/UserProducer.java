//package com.micro.user.service.kafka;
//
//import com.micro.user.service.entities.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserProducer {
//
//    private static final Logger LOGGER=LoggerFactory.getLogger(UserProducer.class);
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    @Value("${my.topic.name}")
//    private String topic;
//
//    @Value(("${my.topic.name.two}"))
//    private String topic2;
//
//    public void sendMessage(String message){
//
//        LOGGER.info("MESSAGE SENT FROM PRODUCER END ->"+message);
//        kafkaTemplate.send(topic,message);
//
//
//    }
//
//    public void sendUserDataMessage(User user){
//
//        LOGGER.info("USER DATA SENT FROM PRODUCER END ->"+user.toString());
//        kafkaTemplate.send(topic2,user.toString());
//
//
//    }
//
//
//
//
//
//
//
//
//}
