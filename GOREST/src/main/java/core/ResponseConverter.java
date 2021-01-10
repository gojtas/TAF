package core;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public class ResponseConverter {
    private static final Logger LOGGER = Logger.getLogger(ResponseConverter.class.getName());
    private final Response response;
    private final String contentType;
    private String responseString;
    private final static String RESPONSE_NULL_CONTENT_TYPE = "response null content type";

    public ResponseConverter(Response response) {
        this.response = response;
        if (!isNullContentPresent()) {
            this.contentType = response.header("Content-Type");
            toStringWithContentType();
        } else {
            LOGGER.log(Level.WARNING, "Response has null contentType");
            this.contentType = RESPONSE_NULL_CONTENT_TYPE;
        }
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(responseString);
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, String.format("Can't invoke jsonObject on string: %s."
                    + " Full exception message: %s", responseString, e));
            jsonObject = new JSONObject("{emptyJsonObject:" + responseString + "}");
        }
        return jsonObject;
    }

    public JSONArray toJsonArray() {
        return new JSONArray(responseString);
    }

    public String retrieveValue(String parameter) {
        String valueToReturn;
        if (isNullContentPresent()) {
            LOGGER.log(Level.WARNING, "null content present, can't retrieve value!");
            valueToReturn = RESPONSE_NULL_CONTENT_TYPE;
        } else valueToReturn = toJsonObject().get(parameter).toString();
        return valueToReturn;
    }

    public String retrieveValue(List<String> parameters) {
        Object metadata;
        try {
            metadata = response.path(getPath(parameters));
        } catch (IllegalStateException e) {
            return RESPONSE_NULL_CONTENT_TYPE;
        }
        return (metadata == null) ? "null" : metadata.toString();
    }

    public static JSONObject convertXMLToJsonObject(String xmlString) {
        return XML.toJSONObject(xmlString);
    }

    public String retrieveDescriptionByLabel(final String label) {
        String issueMessage = "No description found or label is not returned by endpoint";
        if (isNullContentPresent()) {
            LOGGER.log(Level.WARNING, "description will be set manually!");
            return issueMessage;
        }
        JSONArray fieldArray = ((JSONArray) toJsonObject().get("fields"));
        return StreamSupport.stream(fieldArray.spliterator(), false)
                .filter(jsonObject -> isJsonObjectContainingMatchingLabel(label, (JSONObject) jsonObject))
                .map(this::getDescription)
                .findFirst()
                .orElse(String.format(issueMessage, label));
    }

    public JSONArray retrieveNestedElement(String key) {
        return toJsonArray().getJSONObject(toJsonArray().length()
                - 1).getJSONArray(key);
    }

    public JSONArray retrieveElement(String key) {
        return toJsonObject().getJSONArray(key);
    }

    public String getResponseString() {
        return responseString;
    }

    private void toStringWithContentType() {
        this.responseString = response.then().contentType(contentType)
                .extract().response().asString();
    }

    private String getDescription(Object matchingAray) {
        return ((JSONObject) matchingAray).getString("description");
    }

    private boolean isJsonObjectContainingMatchingLabel(String label, JSONObject jsonObject) {
        return jsonObject.get("label").toString().equals(label);
    }

    private String getPath(List<String> parameters) {
        return String.join(".", parameters);
    }

    private boolean isNullContentPresent() {
        return response.contentType().equals("");
    }

}
