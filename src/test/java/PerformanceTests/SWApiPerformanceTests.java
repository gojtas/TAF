package PerformanceTests;

import core.components.SwapiDevComponent;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK_EXPECTED;

class SWApiPerformanceTests {
    private static final Logger logger = Logger.getLogger(SWApiPerformanceTests.class.getName());

    private static final int NUM_LINES_TO_SKIP = 1;

    private static ArrayList<Long> timeList = new ArrayList<>();

    @ParameterizedTest(name = "Path {0} and index {1}.")
    @DisplayName("Element check for SWApi")
    @CsvFileSource(resources = "/PerformanceTests/Swapi.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, int index) {


        Response response = SwapiDevComponent.getSwapiElement(element, index);
        String convertedBody = response.getBody().asString();

        Long transactionTime = response.getTime();
        timeList.add(transactionTime);

        assertThat(response.getStatusCode()).isEqualTo(200);
        logger.log(Level.INFO, STATUS_OK_EXPECTED + " \ntime taken: " +
                transactionTime + " ms" + "\nBody content: " + convertedBody);
    }

    @AfterAll
    static void printStatistics() {
        long deviation = 0;
        for (Long aLong : timeList) {
            deviation += aLong;
        }

        long arithm = deviation / timeList.size();
        logger.log(Level.INFO, "Average response time: " + arithm + " ms.");
    }
}
