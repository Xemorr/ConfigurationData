package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public record EquipableData(ItemStack item, float droprate) {
    // Backwards compatibility with old EquipmentData...
    public static class Deserializer extends JsonDeserializer<EquipableData> {
        @Override
        public EquipableData deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            JsonNode node = parser.getCodec().readTree(parser);
            ItemStackData itemStackData = deserializeItemStackData(parser, context, node);
            float droprate = (float) node.get("droprate").asDouble();
            return new EquipableData(itemStackData.item(), droprate);
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
}
