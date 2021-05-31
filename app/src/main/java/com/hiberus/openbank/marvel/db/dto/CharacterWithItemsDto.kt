package com.hiberus.openbank.marvel.db.dto

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.fasterxml.jackson.databind.JsonNode
import com.hiberus.openbank.marvel.constants.ImageConstants
import com.hiberus.openbank.marvel.db.entities.CharacterItem
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter
import com.hiberus.openbank.marvel.utils.Utils


class CharacterWithItemsDto {

    @Embedded
    var marvelCharacter:MarvelCharacter? =null

    @Relation(parentColumn = "id", entityColumn = "marvelCharacterId", entity = CharacterItem::class)
    var itemList: List<CharacterItem>? = null

    constructor()

    @Ignore
    constructor(jsonNode: JsonNode) {
        this.marvelCharacter = MarvelCharacter()
        this.marvelCharacter!!.id = (jsonNode["id"].asLong())
        this.marvelCharacter!!.name = (jsonNode["name"].asText())
        this.marvelCharacter!!.description = (jsonNode["description"].asText())
        this.marvelCharacter!!.modified = Utils().getDateFromString(jsonNode["modified"].asText())

        //images
        val baseImgUrl = (jsonNode["thumbnail"]["path"].asText())
        val baseImgExtension = (jsonNode["thumbnail"]["extension"].asText())
        this.marvelCharacter!!.thumbnail = baseImgUrl + "/" + ImageConstants.STANDARD.MEDIUM + "." + baseImgExtension
        this.marvelCharacter!!.picture = baseImgUrl + "/" + ImageConstants.PORTRAIT.LARGE + "." + baseImgExtension

        //urls
        jsonNode.findValue("urls").elements().forEach {
            when (it["type"].asText()) {
                "wiki" -> {
                    this.marvelCharacter!!.wiki = it.findValue("url").asText()
                    this.marvelCharacter!!.urlsQty++
                }
                "detail" -> {
                    this.marvelCharacter!!.detail = it.findValue("url").asText()
                    this.marvelCharacter!!.urlsQty++
                }
                "comiclink" -> {
                    this.marvelCharacter!!.comicLink = it.findValue("url").asText()
                    this.marvelCharacter!!.urlsQty++
                }
            }
        }
        //items
        val charItemList :MutableList<CharacterItem> = ArrayList()
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"comics",this.marvelCharacter!!.id))
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"series",this.marvelCharacter!!.id))
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"stories",this.marvelCharacter!!.id))
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"events",this.marvelCharacter!!.id))

        this.itemList = charItemList
    }
    override fun toString(): String {
        return "CharacterWithItemsDto(marvelCharacter=$marvelCharacter, itemList=$itemList\n)"
    }


}