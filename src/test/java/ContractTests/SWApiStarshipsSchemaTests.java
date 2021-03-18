package ContractTests;

import core.SchemaCheck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import readers.InputFileReader;
import utils.dataGenerator.ElementsGenerator;

import static constants.Constants.elementTypeStarships;

@Tag("CONTRACT")
class SWApiStarshipsSchemaTests {

    private String expectedSchema = null;
    private String schemaStarships = "SchemaCheck/StarshipsSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements(elementTypeStarships);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Starships schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Starships")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = InputFileReader.readDataFromFile(schemaStarships);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}
