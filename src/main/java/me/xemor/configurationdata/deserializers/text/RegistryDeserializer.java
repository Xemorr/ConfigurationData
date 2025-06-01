package me.xemor.configurationdata.deserializers.text;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegistryDeserializer<T extends Keyed> extends TextDeserializer<T> {
    private final Registry<T> registry;

    public RegistryDeserializer(Registry<T> registry) {
        this.registry = registry;
    }

    @Override
    public T deserialize(String text, JsonParser parser, DeserializationContext deserializationContext)  {
        if (text == null) return null;
        T value = Optional.ofNullable(NamespacedKey.fromString(text.toLowerCase())).map(registry::get).orElse(null);
        if (value == null) {
            // Get the field name and location (for better error messages)
            String fieldName = null;
            try {
                fieldName = parser.getCurrentName();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String location = parser.getTokenLocation().toString(); // e.g., [Source: (String)"..."; line: 1, column: 20]

            // Get a list of possible values
            String validOptions = registry.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            // Construct a Jackson-style error message
            String errorMessage = String.format(
                    "Invalid value '%s' for field '%s' at %s. Expected one of: [%s]",
                    text, fieldName, location, validOptions
            );

            // Throw as a Jackson `JsonMappingException`
            throw new RuntimeException(JsonMappingException.from(parser, errorMessage));
        }
        return value;
    }
}