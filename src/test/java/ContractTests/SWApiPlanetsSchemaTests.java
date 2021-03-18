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

import static constants.Constants.elementTypePlanets;

@Tag("CONTRACT")
class SWApiPlanetsSchemaTests {

    private String expectedSchema = null;
    private String schemaPlanet = "SchemaCheck/PlanetSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements(elementTypePlanets);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Planet schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Planet")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = InputFileReader.readDataFromFile(schemaPlanet);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}
