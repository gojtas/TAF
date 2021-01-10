package testdata;

public enum MessagesForSWApi {

    STATUS_OK("Expected Status Code OK");
    private final String message;

    MessagesForSWApi(String message) {
        this.message = message;
    }

    public String getUrl() {
        return message;
    }
}
