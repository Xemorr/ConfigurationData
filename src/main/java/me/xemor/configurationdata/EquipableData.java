package me.xemor.configurationdata;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

@JsonDeserialize(using = EquipableData.Deserializer.class)
public record EquipableData(ItemStack item, float droprate) {
    // Backwards compatibility with old EquipmentData...
    public static class Deserializer extends JsonDeserializer<EquipableData> {
        @Override
        public EquipableData deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            JsonNode node = parser.getCodec().readTree(parser);
            ItemStackData itemStackData = context.readValue(node.traverse(), ItemStackData.class);
            float droprate = (float) node.path("droprate").asDouble(0.1);
            return new EquipableData(itemStackData.item(), droprate);
        }
    }
}
