package core.components;

import core.Request;
import core.RequestService;
import io.restassured.response.Response;

import static config.UriProvider.*;

public class SwapiDevComponent {

    private SwapiDevComponent() {
    }

    public static Response getSwapiHealthStatus() {
        Request request = new Request.RequestBuilder()
                .setUri(SWAPIAPI.getUrl())
                .build();
        return RequestService.getOperation(request);
    }

    public static Response getSwapiElement(String element, int elementIndex) {
        Request request = new Request.RequestBuilder()
                .setUri(SWAPIAPI.getUrl())
                .setPath(element + "/" + elementIndex + "/")
                .build();
        return RequestService.getOperation(request);
    }

    public static Response getSwapiElementCounter(String elementType) {
        Request request = new Request.RequestBuilder()
                .setUri(SWAPIAPI.getUrl())
                .setPath("/" + elementType)
                .build();
        return RequestService.getOperation(request);
    }

    public static Response getSearchElement(String element, String keyword) {
        Request request = new Request.RequestBuilder()
                .setUri(SWAPIAPI.getUrl())
                .setPath(element + "/?search=" + keyword)
                .build();
        return RequestService.getOperation(request);
    }
}
