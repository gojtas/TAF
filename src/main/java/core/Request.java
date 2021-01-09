package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String userName;
    private String password;
    private String path;
    private Map<String, Object> queryParamMap;
    private Map<String, Object> formParamMap;
    private String body;
    private String contentType;
    private String uri;
    private Map<String, String> headers;

    @JsonCreator
    private Request(String path,
                    Map<String, Object> queryParamMap,
                    Map<String, Object> formParamMap,
                    String body,
                    String contentType,
                    String uri,
                    String userName,
                    String password,
                    Map<String, String> headers) {
        this.path = path;
        this.queryParamMap = queryParamMap;
        this.formParamMap = formParamMap;
        this.body = body;
        this.contentType = contentType;
        this.uri = uri;
        this.userName = userName;
        this.password = password;
        this.headers = headers;
    }

    public static class RequestBuilder {
        private static final String EMPTY_STRING = "";
        private Map<String, Object> queryParamMap = new HashMap<>();
        private Map<String, Object> formParamMap = new HashMap<>();
        private String path = EMPTY_STRING;
        private String body = EMPTY_STRING;
        private String contentType = "application/json";
        private String uri = EMPTY_STRING;
        private String userName = EMPTY_STRING;
        private String password = EMPTY_STRING;
        private Map<String, String> headers = new HashMap<>();


        public RequestBuilder() {
            queryParamMap.put(EMPTY_STRING, null);
        }

        public Request build() {
            return new Request(
                    path,
                    queryParamMap,
                    formParamMap,
                    body,
                    contentType,
                    uri,
                    userName,
                    password,
                    headers);
        }

        public RequestBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public RequestBuilder setQueryParamMap(Map<String, Object> queryParamMap) {
            this.queryParamMap = queryParamMap;
            return this;
        }

        public RequestBuilder setFormParamMap(Map<String, Object> formParamMap) {
            this.formParamMap = formParamMap;
            return this;
        }

        public RequestBuilder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public RequestBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public RequestBuilder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public RequestBuilder setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public RequestBuilder setAuthUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public RequestBuilder setAuthPassword(String password) {
            this.password = password;
            return this;
        }
    }

    String getPath() {
        return path;
    }

    String getBody() {
        return body;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    String getContentType() {
        return contentType;
    }

    String getUri() {
        return uri;
    }

    Map<String, Object> getQueryParamMap() {
        return queryParamMap;
    }

    Map<String, Object> getFormParamMap() {
        return formParamMap;
    }

    Map<String, String> getHeaders() {
        return headers;
    }
    @Override
    public String toString(){
        return "Request is going to be performed: "
                + "\n Uri: " + getUri()
                + "\n Path: " + getPath()
                + "\n QueryParamMap: " + getQueryParamMap()
                + "\n Body: " + getBody()
                + "\n ContentType: " + getContentType()
                + "\n Headers: " + getHeaders()
                + "\n UserName: " + getUserName()
                + "\n Password: " + getPassword();
    }
}
