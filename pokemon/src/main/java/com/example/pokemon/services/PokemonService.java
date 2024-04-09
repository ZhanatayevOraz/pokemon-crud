package com.example.pokemon.services;

import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.models.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemon();
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemon(int id);
    }

