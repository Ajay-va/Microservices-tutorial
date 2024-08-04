//package com.micro.user.service.kafka;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//public class UserKafkaConfig {
//
//
//    @Value("${my.topic.name}")
//    private String topic;
//
//    @Bean
//    public NewTopic newTopic(){
//
//        return TopicBuilder.name(topic).build();
//    }
//
//    @Bean
//    public NewTopic newTopicTwo(){
//        return TopicBuilder.name(topic).build();
//    }
//
//}
