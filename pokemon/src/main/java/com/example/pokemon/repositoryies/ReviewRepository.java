package com.example.pokemon.repositoryies;

import com.example.pokemon.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByPokemonId(int pokemonId);
}
