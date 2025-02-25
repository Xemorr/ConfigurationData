package me.xemor.configurationdata.deserializers.text;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.xemor.configurationdata.ConfigurationData;

import java.io.IOException;

public abstract class TextDeserializer<T> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String text = jsonParser.getText();
        Deserialized<T> deserialized = deserialize(text);
        JsonLocation location = jsonParser.currentLocation();
        if (deserialized.defaulted() || deserialized.value() == null) {
            ConfigurationData.getLogger().severe("%s cannot deserialize %s at: %s:%s".formatted(
                    getClass().getSimpleName().toLowerCase(),
                    text,
                    location.getLineNr(),
                    location.getColumnNr()
            ));
        }
        return deserialized.value();
    }

    public abstract Deserialized<T> deserialize(String text);

    public T parse(String text) {
        return deserialize(text).value();
    }

    public record Deserialized<T>(T value, boolean defaulted) {}
}
