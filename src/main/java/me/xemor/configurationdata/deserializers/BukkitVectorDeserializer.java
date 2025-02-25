package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.util.Vector;

import java.io.IOException;

public class BukkitVectorDeserializer extends JsonDeserializer<Vector> {
    @Override
    public Vector deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonLocation location = jsonParser.currentLocation();
        if (!node.has("x") || !node.has("y") || !node.has("z"))
            ConfigurationData.getLogger().severe("You have entered an invalid vector at: %s:%s".formatted(location.getLineNr(), location.getColumnNr()));
        double x = node.get("x").asDouble();
        double y = node.get("y").asDouble();
        double z = node.get("z").asDouble();
        return new Vector(x, y, z);
    }
}
