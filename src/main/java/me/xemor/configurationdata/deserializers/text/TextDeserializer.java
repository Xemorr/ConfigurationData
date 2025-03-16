package me.xemor.configurationdata.deserializers.text;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.Bukkit;

import java.io.IOException;

public abstract class TextDeserializer<T> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String text = jsonParser.getText();
        T deserialized = deserialize(text);
        JsonLocation location = jsonParser.currentLocation();
        if (deserialized == null) {
            ConfigurationData.getLogger().severe("%s cannot deserialize %s at: %s:%s".formatted(
                    getClass().getSimpleName().toLowerCase(),
                    text,
                    location.getLineNr(),
                    location.getColumnNr()
            ));
        }
        return deserialized;
    }

    public abstract T deserialize(String text);

    public T parse(String text) {
        return deserialize(text);
    }

    public int getMinorVersion() {
        return Integer.parseInt(Bukkit.getServer().getBukkitVersion().split("-")[0].split("\\.")[1]);
    }
}
