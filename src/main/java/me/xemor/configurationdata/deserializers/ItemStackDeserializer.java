package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.ItemStackData;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemStackDeserializer extends JsonDeserializer<ItemStack> {

    @Override
    public ItemStack deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        ItemStackData itemStackData = deserializeItemStackData(parser, context, node);
        return itemStackData.item();
    }

    private ItemStackData deserializeItemStackData(JsonParser jp, DeserializationContext ctxt,
                                  JsonNode node) throws IOException {
        JavaType javaType = ctxt.getTypeFactory().constructType(ItemStackData.class);
        JsonDeserializer<?> deserializer = ctxt.findRootValueDeserializer(javaType);
        JsonParser parser = node.traverse(jp.getCodec());
        parser.nextToken();
        return (ItemStackData) deserializer.deserialize(parser, ctxt);
    }
}
