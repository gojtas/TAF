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
import java.util.stream.Stream;

import static config.UriProvider.STARSHIPSSCHEMA;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("CONTRACT")
public class SWApiStarshipsSchemaTests {
    public static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements("starships",
                37);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Starships schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Starships: {0}")
    void testSchema(String input) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode inputNode = objectMapper.readTree(new StringReader(input));
        JsonNode schemaNode = objectMapper.readTree(new URL(STARSHIPSSCHEMA.getUrl()));
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance();
        JsonSchema schema = factory.getSchema(schemaNode);

        Set<ValidationMessage> errors = schema.validate(inputNode);
        assertThat(errors).isNotNull().isEmpty();
    }
}
