package com.Payment.RatingService.Controller;

import com.Payment.RatingService.DTO.RatingDTO;
import com.Payment.RatingService.Model.Rating;
import com.Payment.RatingService.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody RatingDTO ratingDTO){
        boolean saveRating = ratingService.createRating(ratingDTO);
        if(saveRating){
            return new ResponseEntity<>("Rating successfully added to database",HttpStatus.CREATED);
        }
        else return new ResponseEntity<>("Failed to add new rating to database",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping
    @RequestMapping("/ratings")
    public ResponseEntity<?> getAllRating(){
        List<Rating> ratingList = ratingService.getAllRatings();
        if(ratingList.isEmpty()){
            return new ResponseEntity<>("No such list of items exists in database",HttpStatus.OK);
        }
        else return ResponseEntity.ok(ratingList);
    }
    @GetMapping
    @RequestMapping("/userRatings/{userId}")
    public ResponseEntity<?> getAllRatingByUserId(@PathVariable int userId){
        List<Rating> ratingList1 = ratingService.getAllRatingsByUserId(userId);
        if(ratingList1.isEmpty()){
            return new ResponseEntity<>("No such list of items exists in database",HttpStatus.OK);
        }
        else return ResponseEntity.ok(ratingList1);
    }

    @GetMapping
    @RequestMapping("/inventoryRatings/{inventoryId}")
    public ResponseEntity<?> getAllRatingByInventoryId(@PathVariable String inventoryId){
        List<Rating> ratingList2 = ratingService.getAllRatingByInventoryId(inventoryId);
        if(ratingList2.isEmpty()){
            return new ResponseEntity<>("No such list of items exists in database",HttpStatus.OK);
        }
        else return ResponseEntity.ok(ratingList2);
    }
    @PutMapping
    public ResponseEntity<?> updateSingleRatingById(String id){
      boolean updateRating = ratingService.updateRatingById(id,new RatingDTO());
      if(updateRating){
          return new ResponseEntity<>("Given rating with id is successfully updated",HttpStatus.CREATED);
      }
      else return new ResponseEntity<>("Rating with given Id could not be updated",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSingleRatingById(String id){
        boolean deleteRating = ratingService.deleteRatingById(id);
        if(!deleteRating){
            return new ResponseEntity<>("Rating could not be deleted",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>("Rating with given id is successfully deleted",HttpStatus.OK);
    }

}
