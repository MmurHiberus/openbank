package com.hiberus.openbank.marvel.apiRest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.hiberus.openbank.marvel.apiRest.deserializers.MarvelCharacterDeserializer
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter


class CharacterListResponse : MarvelResponse() {
    @JsonDeserialize(using = MarvelCharacterDeserializer::class)
    public var characters: List<CharacterWithItemsDto>? = null
    override fun toString(): String {
         return "CharacterListResponse(code=$code, status=$status, characters=$characters)"
    }


}