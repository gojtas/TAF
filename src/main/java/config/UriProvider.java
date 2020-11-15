package config;

public enum UriProvider {

    SWAPIAPI(EnvConfig.getString("SWAPIApi.apiUrl")),
    PLANETSCHEMA(EnvConfig.getString("PLANETSschema.apiUrl")),
    SPECIESSCHEMA(EnvConfig.getString("SPECIESschema.apiUrl")),
    FILMSSCHEMA(EnvConfig.getString("FILMSschema.apiUrl")),
    PEOPLESCHEMA(EnvConfig.getString("PEOPLEschema.apiUrl")),
    STARSHIPSSCHEMA(EnvConfig.getString("STARSHIPSschema.apiUrl")),
    VEHICLESSCHEMA(EnvConfig.getString("VEHICLESschema.apiUrl"));
    private final String baseUri;

    UriProvider(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getUrl() {
        return baseUri;
    }
}
