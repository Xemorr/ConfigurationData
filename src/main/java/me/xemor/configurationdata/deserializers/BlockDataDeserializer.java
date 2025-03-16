package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.deserializers.text.MaterialDeserializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

import java.io.IOException;

public class BlockDataDeserializer extends JsonDeserializer<BlockData> {
    private final MaterialDeserializer materialDeserializer = new MaterialDeserializer();

    @Override
    public BlockData deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        Material material = materialDeserializer.parse(node.path("material").asText("STONE"));

        BlockData blockData = Bukkit.createBlockData(material);

        int level = node.path("level").asInt(-1);
        if (level != -1 && blockData instanceof Levelled levelled) levelled.setLevel(level);
        int age = node.path("age").asInt(-1);
        if (age != -1 && blockData instanceof Ageable ageable) ageable.setAge(age);

        return blockData;
    }
}
