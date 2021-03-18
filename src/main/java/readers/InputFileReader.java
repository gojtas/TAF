package readers;

import utils.dataGenerator.DataFileReader;

public class InputFileReader {

    public static String readDataFromFile(String fileName) {
        return new DataFileReader().read(System.getProperty("user.dir") + "/src/test/resources/" + fileName);
    }
}
