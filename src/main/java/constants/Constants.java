package constants;

public class Constants {
    private Constants() {
    }

    public static final int STATUS_OK = 200;
    public static final int NOT_FOUND_CODE = 404;
    public static final int NO_CONTENT = 204;
    public static final int NUM_LINES_TO_SKIP = 1;
    public static final String CONTENT_TYPE = "application/json";

    public static final String CONNECTION_EXPECTED = "keep-alive";
    public static final String CONNECTION = "Connection";
    public static final String VARY_EXPECTED = "Accept, Cookie";
    public static final String VARY = "Vary";
    public static final String X_FRAME_OPTIONS_EXPECTED = "SAMEORIGIN";
    public static final String X_FRAME_OPTIONS = "X-frame-options";

    public static final String ELEMENT_TYPE_FILMS = "films";
    public static final String ELEMENT_TYPE_PEOPLE = "people";
    public static final String ELEMENT_TYPE_PLANETS = "planets";
    public static final String ELEMENT_TYPE_SPECIES = "species";
    public static final String ELEMENT_TYPE_STARSHIPS = "starships";
    public static final String ELEMENT_TYPE_VEHICLES = "vehicles";

    public final static String JSON_HEADER_VALUE = "application/json";

    public static String EXPECTED_SCHEMA = null;
    public static String SCHEMA_FILMS = "SchemaCheck/FilmSchema.json";
    public static String SCHEMA_PEOPLE = "SchemaCheck/PeopleSchema.json";
    public static String SCHEMA_PLANET = "SchemaCheck/PlanetSchema.json";
    public static String SCHEMA_SPECIES = "SchemaCheck/SpeciesSchema.json";
    public static String SCHEMA_STARSHIPS = "SchemaCheck/StarshipsSchema.json";
    public static String SCHEMA_VEHICLES = "SchemaCheck/VehiclesSchema.json";






}
