package PerformanceTests;

import ComponentTests.SWApiApiElementsTests;
import core.components.GoRestAPIComponent;
import io.restassured.response.Response;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.MessagesForSWApi.STATUS_OK;

@Tag("GORESTCOMPONENT")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoRESTApiPerformanceTests {

    private static final int NUM_LINES_TO_SKIP = 1;
    public static ArrayList<Long> timeList = new ArrayList<>();

    @Order(1)
    @ParameterizedTest(name = "Element {0} with index {1}.")
    @DisplayName("Element check for GOREST")
    @CsvFileSource(resources = "/PerformanceTests/GoRest.csv", numLinesToSkip = NUM_LINES_TO_SKIP)
    void test(String element, int index) {


        Response response = GoRestAPIComponent.getGoRESTElement(element, index);
        String convertedBody = response.getBody().asString();

        Long transactionTime = response.getTime();
        timeList.add(transactionTime);

        assertThat(response.getStatusCode()).isEqualTo(200);
        Logger.getLogger(SWApiApiElementsTests.class.getName()).log(Level.INFO, STATUS_OK + " \ntime taken: " +
                transactionTime + " ms" + "\nBody content: " + convertedBody);
    }

    @Order(2)
    @Test
    void printStatistics() {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (Long i : timeList) {
            descriptiveStatistics.addValue(i);
        }
        double min = descriptiveStatistics.getMin();
        double max = descriptiveStatistics.getMax();
        double mean = descriptiveStatistics.getMean();
        double median = descriptiveStatistics.getPercentile(50);
        double standardDeviation = descriptiveStatistics.getStandardDeviation();

        Logger.getLogger(GoRESTApiPerformanceTests.class.getName()).log(Level.INFO, STATUS_OK +
                "\n Min response time took: " + min +
                "\n Max response time took: " + max +
                "\n Mean response time took: " + mean +
                "\n Median response time took: " + median +
                "\n Standard Deviation response time took: " + standardDeviation);
    }
}
