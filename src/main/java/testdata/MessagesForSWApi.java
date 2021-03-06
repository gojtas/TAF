package testdata;

public enum MessagesForSWApi {

    STATUS_OK_EXPECTED("Expected Status Code Passed");
    private final String message;

    MessagesForSWApi(String message) {
        this.message = message;
    }

    public String getUrl() {
        return message;
    }
}
