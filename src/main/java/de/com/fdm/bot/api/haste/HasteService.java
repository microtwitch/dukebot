package de.com.fdm.bot.api.haste;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class HasteService {
    Logger logger = LoggerFactory.getLogger(HasteService.class);
    private final String url;

    public HasteService(@Value("${haste.url}") String url) {
        this.url = url;
    }

    public String upload(String content) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(content.getBytes(StandardCharsets.UTF_8));
        Request request = new Request.Builder()
                .url(url + "documents")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                logger.error(response.toString());
                return "Error uploading to hastebin";
            }

            Gson gson = new Gson();
            String responseBody = response.body().string();
            HasteResponse hasteResponse = gson.fromJson(responseBody, HasteResponse.class);
            return url + hasteResponse.getKey();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return "Error uploading to hastebin";
        }
    }
}
