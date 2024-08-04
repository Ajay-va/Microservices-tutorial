//package com.micro.user.service.service.impl;
//
//import com.micro.user.service.entities.FileDTO;
//import com.micro.user.service.entities.Hotel;
//import com.micro.user.service.entities.Rating;
//import com.micro.user.service.entities.User;
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FileService {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//
//
//    private final String FILE_PATH = "C:\\Users\\ajay.varma.javvaji\\Documents\\Ajay\\csv";
//
//    public void save(MultipartFile file) throws IOException, CsvException {
//        CSVReader reader = new CSVReader(new FileReader(FILE_PATH + file.getOriginalFilename()));
//
//        List<String[]> rows = reader.readAll();
//        List<FileDTO> filesList = new ArrayList<>();
//
////        for (String[] row : rows) {
////            System.out.println(row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
////
////            Files files = new Files();
////            files.setId(row[0]);
////            files.setDesc1(row[1]);
////            files.setDesc2(row[2]);
////            files.setVal1(row[3]);
////            files.setVal2(row[4]);
////            files.setVal3(row[5]);
////            files.setVal4(row[6]);
////            files.setVal5(row[7]);
////            files.setVal6(row[8]);
////            files.setVal7(row[9]);
////
////            filesList.add(files);
////        }
//
////        fileRepository.saveAll(filesList);
//
//    }
//
//    public List<FileDTO> findAll() {
//
//        Rating[] ratingList=   restTemplate.getForObject("http://RATINGSERVICE/rating/getAllRatings", Rating[].class);
//        User[] usersList=   restTemplate.getForObject("http://USERSERVICE/users/allUsers", User[].class);
//        Hotel[] hotelList=   restTemplate.getForObject("http://HOTELSERVICE/hotel/getAllHotels", Hotel[].class);
//
//        List<FileDTO> fileDTOList=new ArrayList<>();
//
//        FileDTO fileDTO=new FileDTO();
//
//       List<User> userListData= Arrays.asList(usersList);
//       userListData.stream().map(user->{
//           fileDTO.setUserId(user.getUserId());
//           fileDTO.setName(user.getName());
//           fileDTO.setEmail(user.getEmail());
//           fileDTO.setAboutUser(user.getAbout());
//
//           return user;
//       }).collect(Collectors.toList());
//
//      List<Hotel> hotelListData= Arrays.asList(hotelList);
//
//      hotelListData.stream().map(hotel -> {
//
//          fileDTO.setHotelId(hotel.getId());
//          fileDTO.setHotelName(hotel.getName());
//          fileDTO.setAboutHotel(hotel.getAbout());
//          fileDTO.setLocation(hotel.getLocation());
//          return hotel;
//      }).collect(Collectors.toList());
//
//    List<Rating> ratingListData=  Arrays.asList(ratingList);
//
//    ratingListData.stream().map(rating -> {
//
//        fileDTO.setRatingId(rating.getRatingId());
//        fileDTO.setRating(rating.getRating());
//        fileDTO.setFeedback(rating.getFeedback());
//
//      return rating;
//    }).collect(Collectors.toList());
//
//    fileDTOList.add(fileDTO);
//
//   return fileDTOList;
//    }
//
//
//
//
//}
