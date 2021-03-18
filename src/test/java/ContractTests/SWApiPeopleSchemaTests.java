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

import static constants.Constants.elementTypePeople;

@Tag("CONTRACT")
class SWApiPeopleSchemaTests {

    private String expectedSchema = null;
    private String schemaPeople = "SchemaCheck/PeopleSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = ElementsGenerator.generateListOfStarWarsElements(elementTypePeople);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "People schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for People")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = InputFileReader.readDataFromFile(schemaPeople);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}
