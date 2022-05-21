package de.com.fdm.bot.api.github

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class GithubService {
    private val logger = LoggerFactory.getLogger(GithubService::class.java)
    private val client = OkHttpClient()

    fun getLatestCommit() : String {
        val commitsData = getCommits()
        if (commitsData != null) {
            return commitsData[0].sha
        }

        return ""
    }

    private fun getCommits(): List<Commit>? {
        val request: Request = Request.Builder()
            .url("https://api.github.com/repos/microtwitch/dukebot/commits")
            .build()

        return try {
            val response = client.newCall(request).execute()
            val body = response.body?.string()

            val commitType = object : TypeToken<List<Commit>>() {}.type
            Gson().fromJson(body, commitType)
        } catch (e: IOException) {
            logger.error(e.message)
            null
        }
    }
}