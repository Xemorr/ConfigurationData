package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.xemor.configurationdata.ItemStackData;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemStackDeserializer extends JsonDeserializer<ItemStack> {

    @Override
    public ItemStack deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return context.readValue(parser, ItemStackData.class).item();
    }
}
