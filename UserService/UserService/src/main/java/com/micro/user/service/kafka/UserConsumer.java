//package com.micro.user.service.kafka;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.micro.user.service.entities.User;
//import com.micro.user.service.repositories.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserConsumer {
//
//    private static  final Logger LOGGER= LoggerFactory.getLogger(UserConsumer.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @KafkaListener(topics = "${my.topic.name}",groupId="groupId1")
//    public void consume(String message){
//
//        LOGGER.info("MESSAGE RECEIVED AT CONSUMER END -> "+ message);
//        System.out.println("consumer message"+message);
//
//    }
//
//
//    @KafkaListener(topics ="${my.topic.name.two}",groupId = "groupId2")
//    public void UserConsume(User user) throws JsonProcessingException {
//
//        ObjectMapper objectMapper=new ObjectMapper();
//       User userData= objectMapper.readValue("",User.class);
//
//        LOGGER.info("USER MESSAGE DATA RECEIVED TO CONSUMER END -> "+user.toString());
//
//        userRepository.save(userData);
//    }
//
//}
