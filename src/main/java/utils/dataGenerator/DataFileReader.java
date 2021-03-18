package utils.dataGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DataFileReader {
    private static final Logger logger = Logger.getLogger(DataFileReader.class.getName());

    public String read(String stringPath) {
        Path path = Paths.get(stringPath);
        return read(path);
    }

    private String read(Path path) {
        try (Stream<String> ln = Files.lines(path, StandardCharsets.UTF_8)) {
            return ln.collect(Collectors.joining());
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            return "";
        }
    }

    public List<String> getJsonStrings(String jsonsFolder) {
        List<String> sourceJsons = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(jsonsFolder))) {
            paths.filter(Files::isRegularFile).forEach(path -> sourceJsons.add(readFileToString(path)));
        } catch (IOException e) {
            logger.log(Level.WARNING, "Can not read JSON strings", e);
        }
        return sourceJsons;
    }

    private String readFileToString(Path path) {
        return read(System.getProperty("user.dir") + "/" + path);
    }

    public <T> T readObjectFromFile(String stringPath, Class<T> clazz) {
        return mapStringToObject(readFileToString(Paths.get(stringPath)), clazz);
    }

    private  <T> T mapStringToObject(String string, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(string, clazz);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not map JSON string to object.", e);
        }
        return null;
    }
}
