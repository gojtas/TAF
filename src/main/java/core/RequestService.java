package core;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestService {
    private RequestService() {
    }

    public static Response getOperation(Request request){
        return given()
                .baseUri(request.getUri())
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .queryParams(request.getQueryParamMap())
                .headers(request.getHeaders())
                .body(request.getBody())
                .contentType(request.getContentType())
                .auth().basic(request.getUserName(), request.getPassword())
                .get(request.getPath());
    }
}
