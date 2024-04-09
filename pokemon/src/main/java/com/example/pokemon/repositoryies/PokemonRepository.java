package com.example.pokemon.repositoryies;

import com.example.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
}
