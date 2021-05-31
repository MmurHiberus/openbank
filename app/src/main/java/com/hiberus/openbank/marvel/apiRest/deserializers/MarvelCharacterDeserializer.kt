package com.hiberus.openbank.marvel.apiRest.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.hiberus.openbank.marvel.apiRest.dto.CharacterListResponse
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter


class MarvelCharacterDeserializer :
    StdDeserializer<CharacterListResponse?>(CharacterListResponse::class.java) {
    val TAG = "MarvelCharacterDeserializer"
    override fun deserialize(
        jsonParser: JsonParser?,
        ctxt: DeserializationContext?
    ): CharacterListResponse{

        val marvelCharacterList: MutableList<CharacterWithItemsDto> = ArrayList()
        val wrapper = CharacterListResponse()
        val parentNode = jsonParser?.codec?.readTree<JsonNode>(jsonParser)
        wrapper.code = parentNode?.findValue("code")?.asInt()
        wrapper.status = parentNode?.findValue("status")?.asText()
        val node = parentNode?.findValue("results")
        if (node != null) {
            if (node.isArray) {
                for (objNode in node) {
                    val character = CharacterWithItemsDto(objNode)
                    marvelCharacterList.add(character)
                }
            } else {
                val character = CharacterWithItemsDto(node)
                marvelCharacterList.add(character)
            }
            wrapper.characters = marvelCharacterList
        }
        return wrapper
    }

}

