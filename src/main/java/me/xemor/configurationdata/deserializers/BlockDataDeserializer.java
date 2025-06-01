package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.BlockDataData;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.ItemStackData;
import me.xemor.configurationdata.deserializers.text.RegistryDeserializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

import java.io.IOException;

public class BlockDataDeserializer extends JsonDeserializer<BlockData> {

    @Override
    public BlockData deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return context.readValue(parser, BlockDataData.class).getBlockData();
    }
}
