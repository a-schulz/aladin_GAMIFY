package skills.examples.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import skills.examples.data.InitSkillServiceWithData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler
  implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(InitSkillServiceWithData.class);
    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
      throws IOException {

        return (
          httpResponse.getStatusCode().series() == CLIENT_ERROR
          || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        String errorMessage = "";

        // Read the response body and store it in a String
        String responseBody = new BufferedReader(new InputStreamReader(httpResponse.getBody()))
                .lines()
                .collect(Collectors.joining("\n"));

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            errorMessage = "Server error: " + httpResponse.getStatusCode();
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            errorMessage = "Client error: " + httpResponse.getStatusCode();
        }

        // Append the response body (error message) to the errorMessage
        errorMessage += " - Response body: " + responseBody;

        // Extract and append the explanation from the response body
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String explanation = jsonNode.get("explanation").asText();
            errorMessage += " - Explanation: " + explanation;
        } catch (JsonProcessingException | NullPointerException ex) {
            // Handle JSON parsing exception or if "explanation" is null
            log.error("Error parsing response body or extracting explanation.", ex);
        }

        // Log the error message
        log.error(errorMessage);
    }
}