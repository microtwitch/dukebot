package de.com.fdm.bot.api.haste

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class HasteService(@param:Value("\${haste.url}") private val url: String) {
    private val logger = LoggerFactory.getLogger(HasteService::class.java)
    private val client = OkHttpClient()

    fun upload(content: String): String {
        val request: Request = Request.Builder()
                .url(url + "documents")
                .post(content.toRequestBody())
                .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    logger.error(response.toString())
                    return ""
                }

                val gson = Gson()
                val responseBody = response.body?.string()
                val hasteResponse = gson.fromJson(responseBody, HasteResponse::class.java)
                return url + hasteResponse.key
            }
        } catch (e: IOException) {
            logger.error(e.message)
            return ""
        }
    }
}