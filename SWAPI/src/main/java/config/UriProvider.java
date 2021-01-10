package config;

public enum UriProvider {

    SWAPIAPI(EnvConfig.getString("SWAPIApi.apiUrl")),
    PLANETSCHEMA(EnvConfig.getString("SWAPIPLANETSSchema.apiUrl")),
    SPECIESSCHEMA(EnvConfig.getString("SWAPISPECIESschema.apiUrl")),
    FILMSSCHEMA(EnvConfig.getString("SWAPIFILMSschema.apiUrl")),
    PEOPLESCHEMA(EnvConfig.getString("SWAPIPEOPLEschema.apiUrl")),
    STARSHIPSSCHEMA(EnvConfig.getString("SWAPISTARSHIPSschema.apiUrl")),
    VEHICLESSCHEMA(EnvConfig.getString("SWAPIVEHICLESschema.apiUrl")),

    GORESTAPI(EnvConfig.getString("GORESTAPI.apiUrl"));
    private final String baseUri;

    UriProvider(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getUrl() {
        return baseUri;
    }
}
