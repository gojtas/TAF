package dataprovider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

import static constants.Constants.ELEMENT_TYPE_FILMS;
import static utils.dataGenerator.ElementsGenerator.generateListOfStarWarsElements;

public class FilmsDataProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        List<String> elementsList;
        Stream stream = null;
        elementsList = generateListOfStarWarsElements(ELEMENT_TYPE_FILMS);
        for (int i = 0; i < elementsList.size(); i++) {

            stream = Stream.of(
                    Arguments.of(elementsList.get(i))
            );
        }
        return stream;
    }
}
