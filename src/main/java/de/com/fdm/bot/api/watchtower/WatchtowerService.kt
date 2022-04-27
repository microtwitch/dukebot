package de.com.fdm.bot.api.watchtower;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WatchtowerService {
    private final String url;
    private final String token;

    Logger logger = LoggerFactory.getLogger(WatchtowerService.class);

    public WatchtowerService(
            @Value("${watchtower.url}") String url,
            @Value("${watchtower.token}") String token
    ) {
        this.url = url;
        this.token = token;
    }

    public void update() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            logger.info(response.toString());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
