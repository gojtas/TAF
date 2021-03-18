package constants;

public class Constants {
    private Constants() {
    }

    public static final int STATUS_OK = 200;
    public static final int NOT_FOUND_CODE = 404;
    public static final int NUM_LINES_TO_SKIP = 1;
    public static final String CONTENT_TYPE = "application/json";

    public static final String CONNECTION_EXPECTED = "keep-alive";
    public static final String CONNECTION = "Connection";
    public static final String VARY_EXPECTED = "Accept, Cookie";
    public static final String VARY = "Vary";
    public static final String X_FRAME_OPTIONS_EXPECTED = "SAMEORIGIN";
    public static final String X_FRAME_OPTIONS = "X-frame-options";

    public final static String elementTypeFilms = "films";
    public final static String elementTypePeople = "people";
    public final static String elementTypePlanets = "planets";
    public final static String elementTypeSpecies = "species";
    public final static String elementTypeStarships = "starships";
    public final static String elementTypeVehicles = "vehicles";

}
