package de.com.fdm.bot.api.twitch.api

import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.helix.domain.UserList
import com.netflix.hystrix.exception.HystrixRuntimeException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TwitchApiProvider(@param:Value("\${bot.auth}") private val botAuth: String) {
    private val logger = LoggerFactory.getLogger(TwitchApiProvider::class.java)

    private val client: TwitchClient = TwitchClientBuilder
            .builder()
            .withEnableHelix(true)
            .build()

    fun getUserId(userName: String): String? {
        val userList: UserList = try {
            client.helix
                    .getUsers(botAuth, null, listOf(userName))
                    .execute()
        } catch (e: HystrixRuntimeException) {
            logger.error(e.message)
            return null
        }

        val users = userList.users
        if (users.isEmpty()) {
            return null
        }

        return users[0].id
    }

    fun getUserName(userId: String): String? {
        val userList: UserList = try {
            client.helix
                    .getUsers(botAuth, listOf(userId), null)
                    .execute()
        } catch (e: HystrixRuntimeException) {
            logger.error(e.message)
            return null
        }

        val users = userList.users
        if (users.isEmpty()) {
            return null
        }

        return users[0].displayName
    }
}