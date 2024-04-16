package com.example.pokemon.controllers;

import com.example.pokemon.dto.ReviewDto;
import com.example.pokemon.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId")int pokemonId, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto),HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public List<ReviewDto> getReviewDto(@PathVariable(value = "pokemonId")int pokemonId){
        return reviewService.getReviewByPokemonId(pokemonId);
    }

    @GetMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId")int pokemonId, @PathVariable(value = "pokemonId")int reviewId) {
        ReviewDto reviewDto;
        try {
            reviewDto = reviewService.getReviewById(reviewId, pokemonId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }
    @PutMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId")int pokemonId, @PathVariable(value = "pokemonId")int reviewId,@RequestBody ReviewDto reviewDto) {
        ReviewDto updateReviewDto;
        try {
            updateReviewDto = reviewService.updateReview(reviewId, pokemonId,reviewDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(updateReviewDto, HttpStatus.OK);
    }
    @DeleteMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId")int pokemonId, @PathVariable(value = "pokemonId")int reviewId) {
        ReviewDto reviewDto;
        try {
            reviewService.deleteReview(reviewId, pokemonId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
