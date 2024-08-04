package com.micro.user.service.controller;

import com.micro.user.service.entities.FileDTO;
import com.micro.user.service.entities.Hotel;
import com.micro.user.service.entities.User;
import com.micro.user.service.entities.UserDTO;
import com.micro.user.service.externalservice.HotelService;
import com.micro.user.service.payload.APIResponse;
import com.micro.user.service.payload.MailRequestBody;
import com.micro.user.service.repositories.UserRepository;
import com.micro.user.service.service.UserService;

import com.micro.user.service.service.impl.JWTService;

import com.micro.user.service.service.impl.SendEmailSender;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static  final Logger LOGGER= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private FileService fileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendEmailSender emailSender;

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody MailRequestBody requestBody){

        emailSender.sendEmail(requestBody);

        return "MAIL SENT SUCCESSFULLY....!!!";

    }



    @PostMapping("/token")
    public String getToken(@RequestBody UserDTO user){

        Authentication authenticate=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }else {
            throw new UsernameNotFoundException("USER NOT VALIDATED...!!!!");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token){

        System.out.println("TOKEN VALIDATING STARTED...!!!" );
        jwtService.validateToken(token);
        return "Token is valid...!!!";
    }

    @GetMapping("/csvExport")
    public void exportCSV(HttpServletResponse response)
            throws Exception {

        //set file name and content type
        String filename = "user_data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
        StatefulBeanToCsv<User> writer =
                new StatefulBeanToCsvBuilder<User>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
                .build();
        //write all employees data to csv file
        writer.write(userService.findAllUsers());
    }

//    public void exportCsvFile(HttpServletResponse  response) throws IOException {
//
//        response.setContentType("text/csv");
//        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateTime=dateFormat.format(new Date());
//
//        String headerKey="Content-Disposition";
//        String headerValue="attachment; filename=users_"+currentDateTime+".csv";
//        response.setHeader(headerKey,headerValue);
//
//        List<User> listUsers=userService.findAllUsers();
//
////        PrintWriter pw=new PrintWriter();
//
//
//        ICsvBeanReader csvWriter= new CsvBeanReader((ITokenizer) response.getWriter(),
//                CsvPreference.STANDARD_PREFERENCE);
//        String[] csvHeader= {"UserId","Username","Email","Roles","About"};
//        String[] nameMapping={"id","name","email","roles","about"};
//
//        for(User user:listUsers){
//
//
//        }

//    public String saveUser(User userCredentials){
//
//        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
//
//        userCredentialsRepository.save(userCredentials);
//
//
//        return "User added...!!!";
//    }
//
//
//    public String generateToken(String username){
//        return jwtService.generateToken(username);
//    }
//
//    public void validateToken(String token){
//        jwtService.validateToken(token);
//    }

//    @PostMapping("/generateToken")
//    public String validateToken(@RequestBody User user){
//
//        String  token=null;
//
//                Authentication authentication=authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
//
//        if(authentication.isAuthenticated()){
//            token=  jwtService.generateToken(userDetailsService.loadUserByUsername(user.getName()));
//
//        }else {
//            throw new UsernameNotFoundException("INVALID USER FOUND...!!!");
//        }
//        return token;
//    }

//    @GetMapping("/sendMessage/{message}")
//    public String kafkaSample(@PathVariable("message") String message){
//
//        userProducer.sendMessage(message);
//
//        return "Message"+message+"send successfully";
//    }

//    @PostMapping("/sendMessage/kafkaUserData")
//    public String kafkaSample(@RequestBody User user){
//
//        userProducer.sendUserDataMessage(user);
//
//        return "Message send successfully with ID : "+user.getUserId();
//    }



    @PostMapping("/register")
    public APIResponse<User> saveUser(@RequestBody User user){
        APIResponse<User> response=new APIResponse<>();


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userData=userService.saveUser(user);

        List<User> userList=new ArrayList<>();
        userList.add(userData);
        response.setData(userList);

        return response;

    }

    @GetMapping("/allUsers")
    public APIResponse<User> getAllUser(){
        APIResponse<User> apiResponse=new APIResponse<>();
      List<User> userList=  userService.findAllUsers();
        apiResponse.setData(userList);
      return apiResponse;
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallBackMethod")
    public User getUser(@PathVariable("userId") String userId){

      User user=  userService.getById(userId);

      return user;
    }

//    creating fall back method



    public User ratingHotelFallBackMethod(String userId,Exception ex){

        LOGGER.info("Fallback is executed , because service down...!!",ex.getMessage());
        
        User user= User.builder()
                        .userId("1234")
                         .email("dummy@gmail.com")
                         .name("dummy")
                .about("It is dummy data we are using becuase , fall back method")
                .ratingList(null)
                .build();
        return user;

    }


    @GetMapping("/sample/{hotelId}")
    public Hotel sample(@PathVariable String hotelId){

      Hotel hotel= userService.sampleMethod(hotelId);

        return hotel;
    }


//    @GetMapping("/csvExport")
//    public void exportCSV(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
//
//        String fileName = "hotel-rating-file.csv";
//
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "");
//
//        StatefulBeanToCsv<FileDTO> writer = new StatefulBeanToCsvBuilder<FileDTO>(response.getWriter())
//                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
//                .withOrderedResults(true)
//                .build();
//
//        writer.write(fileService.findAll());
//
//
//    }


}
