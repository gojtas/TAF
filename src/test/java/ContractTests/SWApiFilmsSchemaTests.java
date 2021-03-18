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

import static config.UriProvider.FILMSSCHEMA;
import static constants.Constants.elementTypeFilms;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("CONTRACT")
class SWApiFilmsSchemaTests {

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements(elementTypeFilms);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Films schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Films")
    void testSchema(String input) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode inputNode = objectMapper.readTree(new StringReader(input));
        JsonNode schemaNode = objectMapper.readTree(new URL(FILMSSCHEMA.getUrl()));
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance();
        JsonSchema schema = factory.getSchema(schemaNode);


        Set<ValidationMessage> errors = schema.validate(inputNode);
        assertThat(errors).isNotNull().isEmpty();
    }
}
