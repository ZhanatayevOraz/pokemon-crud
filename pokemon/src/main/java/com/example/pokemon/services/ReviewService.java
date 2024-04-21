package com.example.pokemon.services;

import com.example.pokemon.dto.ReviewDto;


import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);

    List<ReviewDto> getReviewByPokemonId(int pokemonId);

    ReviewDto getReviewById(int id, int pokemonId) ;

    ReviewDto updateReview(int id, int pokemonId, ReviewDto reviewDto) throws Exception;

    void deleteReview(int id, int pokemonId)  ;
}
