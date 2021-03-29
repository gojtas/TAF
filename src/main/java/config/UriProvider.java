package config;

public enum UriProvider {

    SWAPIAPI(EnvConfig.getString("SWAPIApi.apiUrl")),
    GORESTURL(EnvConfig.getString("GORESTApi.apiUrl"));
    private final String baseUri;

    UriProvider(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getUrl() {
        return baseUri;
    }
}
