package core.components;

import core.Request;
import core.RequestService;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static config.UriProvider.GORESTAPI;
import static utils.dataGenerator.GoRestElementsGenerator.getBodyForPost;

public class GoRestAPIComponent {

    static String headerParamAuth = "Authorization";
    static String headerValueAuth = "Bearer aaf99feb0bb058e48e0f8c076479958a83453dbb8ec5e1ea827ea5b7349fa2bf";

    private GoRestAPIComponent() {
    }

    public static Response getGoRESTHealthStatus() {
        Request request = new Request.RequestBuilder()
                .setUri(GORESTAPI.getUrl())
                .setPath("/users")
                .build();
        return RequestService.getOperation(request);
    }

    public static Response getGoRESTElement(String element, int elementIndex) {
        Request request = new Request.RequestBuilder()
                .setUri(GORESTAPI.getUrl())
                .setPath(element + "/" + elementIndex + "/")
                .build();
        return RequestService.getOperation(request);
    }

    public static Response postGoRESTElement(String element) {
        Request request = new Request.RequestBuilder()
                .setUri(GORESTAPI.getUrl())
                .setPath(element)
                .setHeaders(setHeaders())
                .setBody(getBodyForPost())
                .build();
        return RequestService.postOperation(request);
    }

    public static Response getGoRESTElementCounter(String elementType) {
        Request request = new Request.RequestBuilder()
                .setUri(GORESTAPI.getUrl())
                .setPath("/" + elementType)
                .build();
        return RequestService.getOperation(request);
    }

    public static Map<String, String> setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(headerParamAuth, headerValueAuth);
        return headers;
    }
}
