package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import testAPI.Authorization;
import org.example.constant.Params;
import org.example.helpers.ReadJson;
import org.example.helpers.RequestHelper;
import org.example.helpers.StringUtils;
import org.example.pojos.request.User;
import org.example.pojos.response.UserResponse;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testAPI.MethodsAPI;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.http.HttpStatus.*;

import java.util.*;

public class Test1 extends BaseTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static RequestHelper requestHelper = new RequestHelper();

    @Test
    @DisplayName("Test")
    public void test1() throws JsonProcessingException {
        //step 1 Получить токен пользователя
        //Получаю статус код 201, ожидание 200 по техзаданию
        accessToken = Authorization.authorizationMethod(
                ReadJson.getTestingDataFromObject("login", "email"),
                ReadJson.getTestingDataFromObject("login", "password")
        );

        //step 2 Зарегистрировать игроков
        List<UserResponse> users = new ArrayList<>();
        List<Params> params_step = List.of(
                new Params("Authorization", "Bearer " + accessToken, Params.ParamType.HEADER));

        for (int i = 0; i < 2; i++) {
            User user =
                    User.createUserBody(
                            StringUtils.getRandomString(6),
                            StringUtils.getRandomString(6),
                            StringUtils.getRandomString(6),
                            StringUtils.getRandomString(6),
                            StringUtils.getRandomString(6),
                            StringUtils.getRandomString(6),
                            StringUtils.getRandomString(6));
            String userBody = MAPPER.writeValueAsString(user);
            Response response = MethodsAPI.createUser(accessToken, userBody);

            assertEquals(SC_CREATED, response.getStatusCode());

            //step 3
            JSONObject getOneBody = new JSONObject();
            getOneBody.put("email", user.getEmail());
            response = MethodsAPI.getOneUser(accessToken, getOneBody.toJSONString());
            String json = response.getBody().asString();
            users.add(MAPPER.readValue(json, UserResponse.class));

            assertEquals(SC_CREATED, response.getStatusCode());
        }

        //step 4
        Response response = MethodsAPI.getAllUsers(accessToken);

        assertEquals(SC_OK, response.getStatusCode());

        String json = response.getBody().asString();
        UserResponse[] userResponse = MAPPER.readValue(json, UserResponse[].class);

        List<UserResponse> listSorted = Arrays.asList(userResponse);

        Collections.sort(listSorted, UserResponse.userResponseComparator);
        for (UserResponse user:listSorted) {
            System.out.println(user.getName());
        }

        //step 5
        for (int i = 0; i < users.size(); i++) {
            response = MethodsAPI.deleteOne(accessToken, users.get(i).getId());
            assertEquals(SC_OK, response.getStatusCode());
        }

        //step 6
        response = MethodsAPI.getAllUsers(accessToken);
        assertEquals(SC_OK, response.getStatusCode());
        json = response.getBody().asString();
        UserResponse[] userResponseWithoutUsers = MAPPER.readValue(json, UserResponse[].class);
        assertTrue(userResponseWithoutUsers.length == 0);


    }
}
