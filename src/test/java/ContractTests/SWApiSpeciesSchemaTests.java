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

import static constants.Constants.elementTypeSpecies;

@Tag("CONTRACT")
class SWApiSpeciesSchemaTests {

    private String expectedSchema = null;
    private String schemaSpecies = "SchemaCheck/SpeciesSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements(elementTypeSpecies);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Species schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Species")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = InputFileReader.readDataFromFile(schemaSpecies);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}