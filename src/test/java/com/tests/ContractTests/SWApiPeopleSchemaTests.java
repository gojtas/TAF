package com.tests.ContractTests;

import core.SchemaCheck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static constants.Constants.elementTypePeople;
import static readers.InputFileReader.readDataFromFile;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

@Tag("CONTRACT")
class SWApiPeopleSchemaTests {

    private String expectedSchema = null;
    private String schemaPeople = "SchemaCheck/PeopleSchema.json";

    static Stream<String> dataProvider() {
        List<String> elementsList;
        elementsList = generateListOfStarWarsElements(elementTypePeople);
        return elementsList.stream();
    }

    @ParameterizedTest(name = "People schema check: {0}")
    @MethodSource("dataProvider")
    @DisplayName("Verify Schema for People")
    void testSchema(String transactionJsonOutput) {

        expectedSchema = readDataFromFile(schemaPeople);
        SchemaCheck.checkSchema(transactionJsonOutput, expectedSchema);
    }
}
