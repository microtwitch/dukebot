package de.com.fdm.bot.api.dynadomain

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DynaDomainService(@param:Value("\${dynadomain.secret}") private val secret: String) {
    private val logger = LoggerFactory.getLogger(DynaDomainService::class.java)
    private val client = OkHttpClient()

    fun setRedirect(subDomain: String, target: String) {
        val redirect = Redirect(secret, subDomain, target)
        val body = Gson().toJson(redirect)

        val request: Request = Request.Builder()
            .url("https://api.feelsdankman.xyz/redirect")
            .header("Content-Type", "application/json")
            .post(body.toRequestBody())
            .build()

        client.newCall(request).execute().use { response ->
            logger.info(response.toString())
        }
    }
}