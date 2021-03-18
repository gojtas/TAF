package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchemaCheck {
    private static final Logger logger = Logger.getLogger(SchemaCheck.class.getName());

    public static void checkSchema(String schemaToVerify, String expectedSchema) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream jsonStream = new ByteArrayInputStream(schemaToVerify.getBytes());
                InputStream schemaStream = new ByteArrayInputStream(expectedSchema.getBytes())
        ) {
            JsonNode json = objectMapper.readTree(jsonStream);
            JsonSchema schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)
                    .getSchema(schemaStream);
            Set<ValidationMessage> validationResult = schemaFactory.validate(json);

            if (validationResult.isEmpty()) {
                logger.info("Schema check passed");
            } else {
                logger.log(Level.WARNING, "Schema not valid, following errors occurred: ");
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
