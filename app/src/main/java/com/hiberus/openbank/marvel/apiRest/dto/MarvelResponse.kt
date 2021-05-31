package com.hiberus.openbank.marvel.apiRest.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.json.JSONArray

@JsonIgnoreProperties(ignoreUnknown = true)
open class MarvelResponse {
    //initialized at 500 as unexpected failure
    @JsonProperty("code")
    var code : Int? = 500

    @JsonProperty("status")
    var status :String? = null
    override fun toString(): String {
        return "MarvelResponse(code=$code, status=$status)"
    }


}