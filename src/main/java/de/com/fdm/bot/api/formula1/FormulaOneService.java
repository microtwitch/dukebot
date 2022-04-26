package de.com.fdm.bot.api.formula1;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.com.fdm.bot.api.formula1.data.MRData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormulaOneService {
    Logger logger = LoggerFactory.getLogger(FormulaOneService.class);

    public MRData getDriverStandings() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://ergast.com/api/f1/current/driverStandings")
                .build();

        try (Response response = client.newCall(request).execute()) {
            XmlMapper mapper = new XmlMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return mapper.readValue(response.body().string(), MRData.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
