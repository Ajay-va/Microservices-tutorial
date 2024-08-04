package com.micro.rating.service.controller;

import com.micro.rating.service.entities.Rating;
import com.micro.rating.service.entities.RatingDTO;
import com.micro.rating.service.service.RatingService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/save")
    public Rating saveRating(@RequestBody Rating rating){

    return ratingService.create(rating);

    }

    @GetMapping("/getAllRatings")
    public List<Rating> getAllRatings(){

        return ratingService.getRatings();

    }

    @GetMapping("/users/{userId}")
    public List<Rating> getAllRatingsByUserId(@PathVariable String userId){

        return ratingService.getRatingByUserId(userId);
    }

    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getAllRatingsByHotelId(@PathVariable("hotelId") String hotelId){

        return ratingService.getRatingByUserId(hotelId);
    }

//    @GetMapping("/exportRatingCsv")
//    public List<RatingDTO> exportRatingData(HttpServletResponse response) throws IOException {
//
//        String filename="ratingData.csv";
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename);
//
//        StatefulBeanToCsv<RatingDTO> csvfileGenerator=
//                new StatefulBeanToCsvBuilder<RatingDTO>(response.getWriter())
//                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
//                .build();
//
//       csvfileGenerator.write(ratingService.getRatings());
//
//        return null;
//
//
//
//    }












}
