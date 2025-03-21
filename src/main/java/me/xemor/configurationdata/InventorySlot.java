package me.xemor.configurationdata;

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

    public class InventorySlotDeserializer extends TextDeserializer<InventorySlot> {

        @Override
        public InventorySlot deserialize(String text) {
            try {
                equipmentSlot = EquipmentSlot.valueOf(text);
            } catch (IllegalArgumentException e) {
                try {
                    slot = Integer.parseInt(text);
                } catch (NumberFormatException ignored) {
                    return null;
                }
            }
            return null;
        }
    }
}
