package de.com.fdm.bot.api.watchtower

import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class WatchtowerService(
        @param:Value("\${watchtower.url}") private val url: String,
        @param:Value("\${watchtower.token}") private val token: String
) {
    private val logger = LoggerFactory.getLogger(WatchtowerService::class.java)
    
    fun update() {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer $token")
                .build()
        
        try {
            client.newCall(request).execute().use {
                response -> logger.info(response.toString())
            }
        } catch (e: IOException) {
            logger.error(e.message)
        }
    }
}