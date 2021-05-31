package com.hiberus.openbank.marvel.utils

import com.fasterxml.jackson.databind.JsonNode
import com.hiberus.openbank.marvel.db.entities.CharacterItem
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    fun getDateFromString(dateStr: String): Date {
        val simpleFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        val dateToConvert = LocalDateTime.parse(dateStr, simpleFormat)
        return Date.from(
            dateToConvert
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )

    }

    fun extractCharacterItems(
        jsonNode: JsonNode,
        field: String,
        characterId: Long?
    ): MutableList<CharacterItem> {
        val charItemList: MutableList<CharacterItem> = ArrayList()
        if (jsonNode.has(field)) {
            jsonNode.findValue(field).findValue("items").elements().forEach {
                val charItem = CharacterItem(it, characterId?:0, field)
                charItemList.add(charItem)
            }
        }
        return charItemList


    }
}