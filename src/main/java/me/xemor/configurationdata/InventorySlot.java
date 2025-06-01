package me.xemor.configurationdata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.xemor.configurationdata.deserializers.text.TextDeserializer;
import org.bukkit.inventory.EquipmentSlot;

@JsonDeserialize(using = InventorySlot.InventorySlotDeserializer.class)
public class InventorySlot {

    private EquipmentSlot equipmentSlot;
    private int slot;

    public InventorySlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public InventorySlot(int slot) {
        this.slot = slot;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public int getSlot() {
        return slot;
    }

    public static class InventorySlotDeserializer extends TextDeserializer<InventorySlot> {

        @Override
        public InventorySlot deserialize(String text, JsonParser jsonParser, DeserializationContext deserializationContext) {
            try {
                return new InventorySlot(EquipmentSlot.valueOf(text));
            } catch (IllegalArgumentException e) {
                try {
                    return new InventorySlot(Integer.parseInt(text));
                } catch (NumberFormatException ignored) {
                    return null;
                }
            }
        }
    }
}
