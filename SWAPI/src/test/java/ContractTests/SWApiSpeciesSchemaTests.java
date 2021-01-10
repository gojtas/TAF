package ContractTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.dataGenerator.ElementsGenerator;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static config.UriProvider.SPECIESSCHEMA;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("CONTRACT")
public class SWApiSpeciesSchemaTests {
    final static String elementType = "species";

    private static final Logger LOGGER = Logger.getLogger(SWApiSpeciesSchemaTests.class.getName());

    public static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements(elementType);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Species schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Species")
    void testSchema(String input) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode inputNode = objectMapper.readTree(new StringReader(input));
        JsonNode schemaNode = objectMapper.readTree(new URL(SPECIESSCHEMA.getUrl()));
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance();
        JsonSchema schema = factory.getSchema(schemaNode);

        try {
            Set<ValidationMessage> errors = schema.validate(inputNode);
            assertThat(errors).isNotNull().isEmpty();
        } catch (AssertionError e) {
            LOGGER.log(Level.WARNING,  "\n" + "Exception in Species: "
                    + inputNode.get("name") + e.getLocalizedMessage());
        }
    }
}
