package de.com.fdm.bot.api.formula1

import com.google.gson.Gson
import de.com.fdm.bot.api.formula1.data.constructor.ConstructorStandingsData
import de.com.fdm.bot.api.formula1.data.driver.DriverStandingsData
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class FormulaOneService {
    private val logger = LoggerFactory.getLogger(FormulaOneService::class.java)
    private val client = OkHttpClient()

    fun getStandings() : DriverStandingsData? {
        val request: Request = Request.Builder()
                .url("http://ergast.com/api/f1/current/driverStandings.json")
                .build()

        return try {
            val response = client.newCall(request).execute()
            val body = response.body?.string()
            Gson().fromJson(body, DriverStandingsData::class.java)
        } catch (e: IOException) {
            logger.error(e.message)
            null
        }
    }

    fun getConstructorStandings() : ConstructorStandingsData? {
        val request: Request = Request.Builder()
            .url("http://ergast.com/api/f1/current/constructorStandings.json")
            .build()

        return try {
            val response = client.newCall(request).execute()
            val body = response.body?.string()
            Gson().fromJson(body, ConstructorStandingsData::class.java)
        } catch (e: IOException) {
            logger.error(e.message)
            null
        }
    }
}