package com.hiberus.openbank.marvel.db.entities

import android.util.Log
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Relation
import com.fasterxml.jackson.databind.JsonNode
import com.hiberus.openbank.marvel.constants.ImageConstants
import com.hiberus.openbank.marvel.utils.Utils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalField
import java.util.*
import kotlin.collections.ArrayList

@Entity(
    tableName = "MarvelCharacter",
    primaryKeys = ["id"]
)
class MarvelCharacter {

    var id: Long? = null

    var name: String? = null

    var description: String? = null

    var modified: Date? = null

    //url thumb
    var thumbnail: String? = null

    //url image
    var picture: String? = null

    var detail: String? = null
    var wiki: String? = null
    var comicLink: String? = null
    var urlsQty :Int=0

    //@Relation(parentColumn = "id", entityColumn = "marvelCharacterId", entity = CharacterItem::class)
    //var itemList: List<CharacterItem>? = null

    constructor()

   /* @Ignore
    constructor(jsonNode: JsonNode) {
        this.id = (jsonNode["id"].asLong())
        this.name = (jsonNode["name"].asText())
        this.description = (jsonNode["description"].asText())
        this.modified = Utils().getDateFromString(jsonNode["modified"].asText())

        //images
        val baseImgUrl = (jsonNode["thumbnail"]["path"].asText())
        val baseImgExtension = (jsonNode["thumbnail"]["extension"].asText())
        this.thumbnail = baseImgUrl + "/" + ImageConstants.STANDARD.SMALL + "." + baseImgExtension
        this.picture = baseImgUrl + "/" + ImageConstants.PORTRAIT.MEDIUM + "." + baseImgExtension

        //urls
        jsonNode.findValue("urls").elements().forEach {
            when (it["type"].asText()) {
                "wiki" -> {
                    this.wiki = it.findValue("url").asText()
                    this.urlsQty++
                }
                "detail" -> {
                    this.detail = it.findValue("url").asText()
                    this.urlsQty++
                }
                "comiclink" -> {
                    this.comicLink = it.findValue("url").asText()
                    this.urlsQty++
                }
            }
        }
        //items
        val charItemList :MutableList<CharacterItem> = ArrayList()
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"comics",this.id))
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"series",this.id))
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"stories",this.id))
        charItemList.addAll(Utils().extractCharacterItems(jsonNode,"events",this.id))

       this.itemList = charItemList
    }
*/
    override fun toString(): String {
        return "MarvelCharacter(id=$id, name=$name, description=$description, modified=$modified, thumbnail=$thumbnail, picture=$picture, detail=$detail, wiki=$wiki, comicLink=$comicLink, urlsQty=$urlsQty)"
    }


}