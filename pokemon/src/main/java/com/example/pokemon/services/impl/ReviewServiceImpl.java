package com.example.pokemon.services.impl;

import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.dto.ReviewDto;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.models.Review;
import com.example.pokemon.repositoryies.PokemonRepository;
import com.example.pokemon.repositoryies.ReviewRepository;
import com.example.pokemon.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

   private final PokemonRepository pokemonRepository;
   private final ReviewRepository reviewRepository;

   @Autowired
   public ReviewServiceImpl(PokemonRepository pokemonRepository, ReviewRepository reviewRepository) {
        this.pokemonRepository = pokemonRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow();
        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(int pokemonId) {
        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);
        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int id, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new NoSuchElementException("No Pokemon found with ID: " + pokemonId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Review found with ID: " + id));

        if (pokemon.getId() != review.getPokemon().getId()) {
            throw new IllegalArgumentException("Review ID " + id + " does not belong to Pokemon ID " + pokemonId);
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int id, int pokemonId, ReviewDto reviewDto) throws Exception {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow();

        Review review = reviewRepository.findById(id).orElseThrow();

        if(pokemon.getId() != review.getPokemon().getId()){
            throw new Exception("Pokemon doesn't have this review");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updateReview = reviewRepository.save(review);
        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(int id, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new EmptyResultDataAccessException("No Pokemon found with ID: " + pokemonId, 1));
        Review review = reviewRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No Review found with ID: " + id, 1));

        if(pokemon.getId() != review.getPokemon().getId()){
            throw new IllegalArgumentException("Review ID " + id + " does not belong to Pokemon ID " + pokemonId);
        }
        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setStars(review.getStars());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());

        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto){
       Review review = new Review();
       review.setContent(reviewDto.getContent());
       review.setId(reviewDto.getId());
       review.setTitle(reviewDto.getTitle());
       review.setStars(reviewDto.getStars());
       return review;
    }
}
