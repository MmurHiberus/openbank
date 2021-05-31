package com.hiberus.openbank.marvel.apiRest.restImplementation

import com.hiberus.openbank.marvel.BuildConfig
import com.hiberus.openbank.marvel.apiRest.config.RetrofitClient
import java.math.BigInteger
import java.security.MessageDigest

open class BaseServiceImpl {
    private val apiKey = BuildConfig.API_PUBLIC_KEY
    private val privateKey = BuildConfig.API_PRIVATE_SECRET

    fun getHash(ts: String): String {
        return md5(ts+ privateKey + apiKey)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}