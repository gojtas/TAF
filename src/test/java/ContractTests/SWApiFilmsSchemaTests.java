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

import static constants.Constants.elementTypeFilms;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

@Tag("CONTRACT")
class SWApiFilmsSchemaTests {

    private String expectedSchema = null;
    private String schemaFilms = "SchemaCheck/FilmSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = generateListOfStarWarsElements(elementTypeFilms);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "Films schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for Films")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = readDataFromFile(schemaFilms);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}
