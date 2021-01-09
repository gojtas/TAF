package PerformanceTests;

import ComponentTests.SWApiApiElementsTests;
import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK;

public class SWApiPerformanceTests {

    private static final int NUM_LINES_TO_SKIP = 1;

    public static ArrayList<Long> timeList = new ArrayList<>();

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Element check for SWApi")
    @CsvFileSource(resources = "/PerformanceTests/Swapi.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, int index) {


        Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        Long transactionTime = response.getTime();
        timeList.add(transactionTime);

        assertThat(response.getStatusCode()).isEqualTo(200);
        Logger.getLogger(SWApiApiElementsTests.class.getName()).log(Level.INFO, STATUS_OK + " \ntime taken: " +
                transactionTime + " ms" + "\nBody content: " + convertedBody);
    }

    @AfterAll
    static void printStatistics() {
        long deviation = 0;
        for (int i = 0; i < timeList.size(); i++) {
            deviation += timeList.get(i);
        }
        long arithm = deviation / timeList.size();
        System.out.println(arithm);
    }
}
