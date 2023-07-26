package testAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Params;
import org.example.constant.RequestAPI;
import org.example.helpers.ReadJson;
import org.example.helpers.RequestHelper;
import org.json.simple.JSONObject;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Authorization {
    public static Response response;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static RequestHelper requestHelper = new RequestHelper();
    static String accessToken;

    public static String authorizationMethod(String email, String password){
        List<Params> params_precond_1 = List.of(
                new Params("Content-Type", "application/json", Params.ParamType.HEADER)
        );

        JSONObject requestParams = new JSONObject();
        requestParams.put("password", password);
        requestParams.put("email", email);


        response = requestHelper.sendRequest(
                ReadJson.getTestingDataFromObject("url","serviceTester"),
                ReadJson.getTestingDataFromObject("url","login"),
                RequestAPI.POST.getFormatReq(), params_precond_1, requestParams.toJSONString());

        assertEquals(SC_CREATED, response.getStatusCode());

        JsonNode root;
        try {
            root = MAPPER.readValue(response.getBody().asString(), JsonNode.class);
        } catch (JsonProcessingException ex) {
            log.info("Problems with mapping of content and ex:" + ex);
            throw new RuntimeException(ex);
        }

        accessToken = root.path("accessToken").toString();

        return accessToken.substring(1, accessToken.length()-1);
    }
}

