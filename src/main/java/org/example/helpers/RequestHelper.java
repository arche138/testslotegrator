package org.example.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import org.example.constant.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestHelper {
    Logger logger = LoggerFactory.getLogger(RequestHelper.class);
    public Response sendRequest(String service, String serviceMethod, String requestMethod,
                                List<Params> params, Object body) {

        String url = ReadJson.getTestingDataFromObject("url", "host") + service + serviceMethod;

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(body);
        requestSpecification.log().all();

        for (Params param : params) {
            switch (param.getType()) {
                case HEADER:
                    requestSpecification.header(param.getKey(), param.getValue());
                    break;
                case PARAM:
                    requestSpecification.param(param.getKey(), param.getValue());
                    break;
                case BODY:
                    requestSpecification.body(body);
                    break;
                default:
                    logger.info("Invalid ParamType: " + param.getType());
                    throw new IllegalArgumentException("Invalid ParamType: " + param.getType());
            }
        }
        Response response = requestSpecification.request(requestMethod, url);
        System.out.println(response.getBody().asPrettyString());
        logger.info("Response body: {}", response.getBody().asPrettyString());
        return response;
    }
}
