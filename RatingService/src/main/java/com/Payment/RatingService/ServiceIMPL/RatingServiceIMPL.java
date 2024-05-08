package com.Payment.RatingService.ServiceIMPL;

import com.Payment.RatingService.DTO.RatingDTO;
import com.Payment.RatingService.Exception.BadRequestException;
import com.Payment.RatingService.Exception.ResourceNotFoundException;
import com.Payment.RatingService.Model.Rating;
import com.Payment.RatingService.Repository.RatingRepository;
import com.Payment.RatingService.Service.RatingService;
import jakarta.ws.rs.OPTIONS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RatingServiceIMPL implements RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Override
    public boolean createRating(RatingDTO ratingDTO) {
        if (ratingDTO.getInventoryId() == null ||
                ratingDTO.getInventoryId().isEmpty() ||
                ratingDTO.getRating() == 0 ||
                ratingDTO.getRating()<1 ||
                ratingDTO.getRating()>5 ||
                ratingDTO.getUserId()==0) {
            throw new BadRequestException("Above fields cannot be empty or null!");
        }

        Rating rating = new Rating();
        BeanUtils.copyProperties(ratingDTO,rating);
        try {
            Optional<Rating> savedRating = Optional.of(ratingRepository.save(rating));
            return true;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public List<Rating> getAllRatings() {
        List<Rating> allRating = ratingRepository.findAll();
        return allRating;
    }

    @Override
    public List<Rating> getAllRatingsByUserId(int userId) {
        List<Rating> allRating = ratingRepository.findByUserId(userId);
        return allRating;
    }

    @Override
    public List<Rating> getAllRatingByInventoryId(String inventoryId) {
        List<Rating> allRating = ratingRepository.findByInventoryId(inventoryId);
        return allRating;
    }

    @Override
    public boolean updateRatingById(String id, RatingDTO ratingDTO) {
        Optional<Rating> fetchedItem=ratingRepository.findById(id);
        //add check to compare ownerId with id from token, if match then proceed else throw UnauthorizedException
        if(fetchedItem.isEmpty())throw new ResourceNotFoundException("Cannot find item with given id!");
        Rating item=fetchedItem.get();
        item.setRating(ratingDTO.getRating());
        item.setReview(ratingDTO.getReview());
        item.setUserId(ratingDTO.getUserId());
        item.setInventoryId(ratingDTO.getInventoryId());
        try{
            Optional<Rating> updatedItem=Optional.of(ratingRepository.save(item));
            return true;
        } catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean deleteRatingById(String id) {
        Optional<Rating> fetchedItem=ratingRepository.findById(id);
        //check ownerId in fetchedItem with id in token, if match then proceed else thor UnauthorizedException
        if(fetchedItem.isPresent()){
            ratingRepository.deleteById(id);
            return true;
        } else{
            throw new ResourceNotFoundException("Process Selected for this particular item cannot be performed");
        }
    }
}
