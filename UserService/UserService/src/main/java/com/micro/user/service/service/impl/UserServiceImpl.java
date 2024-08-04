package com.micro.user.service.service.impl;

import com.micro.user.service.entities.Hotel;
import com.micro.user.service.entities.Rating;
import com.micro.user.service.entities.User;
import com.micro.user.service.exceptions.ResourceNotFoundException;
import com.micro.user.service.externalservice.HotelService;
import com.micro.user.service.externalservice.RatingService;
import com.micro.user.service.repositories.UserRepository;
import com.micro.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        User userData=new User();

        if(user!=null){
            String userId=  UUID.randomUUID().toString();
            user.setUserId(userId);
           userData= userRepository.save(user);
        }

        return userData;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(String userId) {


//        http://localhost:8081/rating/users/4ab4f47c-6f93-43d6-b2ba-df13dc5d4b1c

       User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User id "+userId+" not found"));

//    Rating[] ratingList=   restTemplate.getForObject("http://RATINGSERVICE/rating/users/"+user.getUserId(), Rating[].class);

   List<Rating> ratingList= ratingService.getAllRatingList();
        user.setRatingList(ratingList);
//   List<Rating> ratingListData=ratingList.stream().flatMap(rating->{
//       Hotel hotel=hotelService.getHotelId(rating.getHotelId());
//       rating.setHotel(hotel);
//       return rating;
//   }).collect(Collectors.toList());
//        System.out.println(ratingListData);
   return user;



//        user.setRatingList(ratingList);
//    log.info("{}",ratingList);
//        System.out.println("ratingList ==>"+ratingList);

//  List<String> hotelIds=  ratingList.stream().map(rating->rating.getHotelId()).collect(Collectors.toList());
//        System.out.println("hotelsId ==> "+hotelIds);
//     List<Rating> ratings=   Arrays.stream(ratingList).toList();

//    ratingList.stream().map(rating->{
//        System.out.println(rating.getHotelId());
//
////        http://localhost:8082/hotel/hotels/0a7c0b98-19b0-415f-b282-4b8f718185c2
//
////      ResponseEntity<Hotel> hotelResponse=  restTemplate.getForEntity("http://HOTELSERVICE/hotel/hotels/"+rating.getHotelId(), Hotel.class);
//
//      Hotel hotelData=  hotelService.getHotelId(rating.getHotelId());
//
////      Hotel hotelData=hotelResponse.getBody();
//
//      rating.setHotel(hotelData);
//
//      return rating;
//
//    }).collect(Collectors.toList());
//    user.setRatingList(ratingList);
//
//       return user;

    }

    @Override
    public void deleteUserId(String userId) {

        if(userId!=null){
            userRepository.deleteById(userId);
        }


    }

    @Override
    public User updateUser(User user) {
        User userNewData=new User();
        if(user!=null && user.getUserId()!=null){
            User userData= userRepository.findById(user.getUserId()).get();
            userNewData.setName(user.getName());
            userNewData.setAbout(user.getAbout());
            userNewData.setEmail(user.getEmail());

            userRepository.save(userNewData);
        }
        return userNewData;
    }



    public Hotel sampleMethod(String hotelId){

        return hotelService.getHotelId(hotelId);

    }

//    @Override
//    public ByteArrayInputStream loadData() {
//
//    List<User> userList=userRepository.findAll();
//    ByteArrayInputStream stream=userToCSV(userList);
//
//
//
//        return stream;
//    }

//    private ByteArrayInputStream userToCSV(List<User> userList) {
//
//
//    CSVFormat format=CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//    try(ByteArrayOutputStream out=new ByteArrayOutputStream();
//        CSVPrinter csvPrinter=new CSVPrinter(new PrintWriter(out),format);
//    ){
//    List<User> userListData=userList.stream().map(m->m).collect(Collectors.toList());
//
//        System.out.println(userListData);
//    csvPrinter.printRecords(userListData);
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//
//    return  null;
//    }
}