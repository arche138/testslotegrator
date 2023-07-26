package testAPI;

import io.restassured.response.Response;
import org.example.constant.Params;
import org.example.constant.RequestAPI;
import org.example.helpers.ReadJson;
import org.example.helpers.RequestHelper;

import java.util.List;

public class MethodsAPI {
    private static final RequestHelper requestHelper = new RequestHelper();

    public static Response createUser(String accessToken, String userBody){
        List<Params> params_step = List.of(
                new Params("Authorization", "Bearer " + accessToken, Params.ParamType.HEADER));

        Response response = requestHelper.sendRequest(
                ReadJson.getTestingDataFromObject("url", "automationTask"),
                ReadJson.getTestingDataFromObject("url", "create"),
                RequestAPI.POST.getFormatReq(), params_step, userBody);
        return response;
    }

    public static Response getOneUser(String accessToken, String userBody){
        List<Params> params_step = List.of(
                new Params("Authorization", "Bearer " + accessToken, Params.ParamType.HEADER));

        Response response = requestHelper.sendRequest(
                ReadJson.getTestingDataFromObject("url", "automationTask"),
                ReadJson.getTestingDataFromObject("url", "getOne"),
                RequestAPI.POST.getFormatReq(), params_step, userBody);
        return response;
    }

    public static Response getAllUsers(String accessToken){
        List<Params> params_step = List.of(
                new Params("Authorization", "Bearer " + accessToken, Params.ParamType.HEADER));

        Response response = requestHelper.sendRequest(
                ReadJson.getTestingDataFromObject("url", "automationTask"),
                ReadJson.getTestingDataFromObject("url", "getAll"),
                RequestAPI.GET.getFormatReq(), params_step, "");
        return response;
    }

    public static Response deleteOne(String accessToken, String id){
        List<Params> params_step = List.of(
                new Params("Authorization", "Bearer " + accessToken, Params.ParamType.HEADER));

        Response response = requestHelper.sendRequest(
                ReadJson.getTestingDataFromObject("url", "automationTask"),
                ReadJson.getTestingDataFromObject("url", "deleteOne")+"/" + id,
                RequestAPI.DELETE.getFormatReq(), params_step, "");
        return response;
    }
}
