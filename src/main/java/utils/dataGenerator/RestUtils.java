package utils.dataGenerator;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class RestUtils {
    private static final Logger logger = Logger.getLogger(RestUtils.class);
    private static final LogDetail requestLogFilterDetails = LogDetail.ALL;
    private static RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(requestLogFilterDetails);
    private static String path = "";

    private RestUtils() {
    }

    public static void setFullUri(String baseUri, String basePath) {
        setBaseUri(baseUri);
        setBasePath(basePath);
    }

    private static void setBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    private static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    public static void setBaseAuth(String user, String password) {
        RestAssured.basic(user, password);
    }

    public static void resetBaseUri() {
        RestAssured.baseURI = null;
    }

    public static void resetBasePath() {
        RestAssured.basePath = null;
    }

    public static void resetPathParams() {
        path = null;
    }

    private static void setPathParams(Map<String, String> pathParams) {
        StringBuilder sb = new StringBuilder("?");
        appendAllParams(pathParams.entrySet().iterator(), sb);

        path = sb.toString();
    }

    private static void appendAllParams(Iterator<Map.Entry<String, String>> iterator, StringBuilder sb) {
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            if (iterator.hasNext()) {
                sb.append("&");
            }
        }
    }

    public static Response getResponse(RequestSpecification requestSpecification) {
        logger.info(requestSpecification.filter(requestLoggingFilter));
        return requestSpecification.get(path);
    }

    private static Response putResponse(RequestSpecification requestSpecification) {
        logger.info(requestSpecification.filter(requestLoggingFilter));
        return requestSpecification.put(path);
    }

    public static Response deleteResponse(RequestSpecification requestSpecification) {
        logger.info(requestSpecification.filter(requestLoggingFilter));
        return requestSpecification.delete(path);
    }

    public static Response postResponse(RequestSpecification requestSpecification) {
        logger.info(requestSpecification.filter(requestLoggingFilter));
        return requestSpecification.post(path);
    }

    public static Response patchResponse(RequestSpecification requestSpecification) {
        logger.info(requestSpecification.filter(requestLoggingFilter));
        return requestSpecification.patch(path);
    }

    private static JsonPath getJsonPath(Response res) {
        return new JsonPath(res.asString());
    }

    public static <T> T parseObject(Response res, Class<T> clazz) {
        return getJsonPath(res).getObject("$", clazz);
    }

    public static String getBodyAsString(Response res) {
        return res.getBody().asString();
    }

    private static RequestSpecBuilder setHeaders(Map<String, String> headersMap) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        headersMap.forEach(requestSpecBuilder::addHeader);
        return requestSpecBuilder;
    }

    public static RequestSpecification getRequestSpecificationWithHeaders(Map<String, String> headersMap) {
        return RestAssured.given().spec(setHeaders(headersMap).build());
    }

    public static RequestSpecification getRequestSpecificationWithBasicAuth(String user, String password) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(user);
        auth.setPassword(password);
        requestSpecBuilder.setAuth(auth);
        return requestSpecBuilder.build();
    }

    public static RequestSpecification getRequestSpecificationWithJsonBody(String jsonBody, String headerValue) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.addHeader("Accept", headerValue);
        requestSpecBuilder.addHeader("Authorization", "Bearer aaf99feb0bb058e48e0f8c076479958a83453dbb8ec5e1ea827ea5b7349fa2bf");
        requestSpecBuilder.setBody(jsonBody);
        return RestAssured.given().spec(requestSpecBuilder.build());
    }

    public static RequestSpecification getRequestSpecificationWithJsonBody(String jsonBody, String headerValue, String reqId) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.addHeader("Accept", headerValue);
        requestSpecBuilder.addHeader("Authorization", "Bearer aaf99feb0bb058e48e0f8c076479958a83453dbb8ec5e1ea827ea5b7349fa2bf");
        requestSpecBuilder.setBasePath("/users/" + reqId);
        requestSpecBuilder.setBody(jsonBody);
        return RestAssured.given().spec(requestSpecBuilder.build());
    }

    public static RequestSpecification getRequestSpecificationWithJsonBody(String jsonBody, String headerValue,
                                                                           String assetHeader, String assetHeaderValue) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.addHeader("Accept", headerValue);
        requestSpecBuilder.addHeader(assetHeader, assetHeaderValue);
        requestSpecBuilder.setBody(jsonBody);
        return RestAssured.given().spec(requestSpecBuilder.build());
    }

    public static RequestSpecification getRequestSpecificationWithoutBody(String headerValue, String reqId) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.addHeader("Accept", headerValue);
        requestSpecBuilder.addHeader("Authorization", "Bearer aaf99feb0bb058e48e0f8c076479958a83453dbb8ec5e1ea827ea5b7349fa2bf");
        requestSpecBuilder.setBasePath("/users/" + reqId);
        return RestAssured.given().spec(requestSpecBuilder.build());
    }

    public static RequestSpecification getRequestSpecificationWithoutBodyForGet(String headerValue) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.addHeader("Accept", headerValue);
        requestSpecBuilder.setBasePath("/users");
        return RestAssured.given().spec(requestSpecBuilder.build());
    }

    public static Response makeGetRequestWithParams(RequestSpecification requestSpecification,
                                                    Map<String, String> params) {
        RestUtils.setPathParams(params);
        return RestUtils.getResponse(RestAssured.given(requestSpecification));
    }

    public static Response makeGetRequestWithBasicAuth(RequestSpecification requestSpecification) {
        return RestUtils.getResponse(RestAssured.given(requestSpecification));
    }

    public static Response makePutRequestWithParams(RequestSpecification requestSpecification,
                                                    Map<String, String> params) {
        RestUtils.setPathParams(params);
        return RestUtils.putResponse(requestSpecification);
    }

    public static Response makeDeleteRequestWithParams(RequestSpecification requestSpecification,
                                                       Map<String, String> params) {
        RestUtils.setPathParams(params);
        return RestUtils.deleteResponse(requestSpecification);
    }

    public static Response makePostRequestWithParams(RequestSpecification requestSpecification,
                                                     Map<String, String> params) {
        RestUtils.setPathParams(params);
        return RestUtils.postResponse(requestSpecification);
    }
}