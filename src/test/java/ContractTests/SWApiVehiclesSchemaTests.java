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

import static constants.Constants.elementTypeVehicles;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

@Tag("CONTRACT")
class SWApiVehiclesSchemaTests {

    private String expectedSchema = null;
    private String schemaVehicles = "SchemaCheck/VehiclesSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = generateListOfStarWarsElements(elementTypeVehicles);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Vehicles schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Vehicles")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = readDataFromFile(schemaVehicles);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}
