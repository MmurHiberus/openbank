package com.hiberus.openbank.marvel.db.entities

import android.util.Log
import androidx.room.Entity
import androidx.room.Ignore
import com.fasterxml.jackson.databind.JsonNode
import com.hiberus.openbank.marvel.constants.ImageConstants
import com.hiberus.openbank.marvel.utils.Utils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalField
import java.util.*

@Entity(
    tableName = "CharacterItem",
    primaryKeys = ["marvelCharacterId", "resourceURi"]
)
class CharacterItem {
    var marvelCharacterId: Long = 0
    var resourceURi: String = ""
    var itemKind: String? = null


    var itemName: String? = null

    var type: String? = null

    constructor()

    @Ignore
    constructor(jsonNode: JsonNode, characterId: Long, kind: String) {
        this.marvelCharacterId = characterId
        this.itemKind = kind

        this.itemName = (jsonNode["name"].asText())
        this.resourceURi = (jsonNode["resourceURI"].asText())
        if (jsonNode.has("type")) {
            this.type = (jsonNode["type"].asText())
        }
    }

    override fun toString(): String {
        return "CharacterItem(marvelCharacterId=$marvelCharacterId, resourceURi='$resourceURi', itemKind=$itemKind, name=$itemName, type=$type)"
    }


}