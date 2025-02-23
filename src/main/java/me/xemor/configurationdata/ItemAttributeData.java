package me.xemor.configurationdata;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemAttributeData {

    Map<Attribute, AttributeModifier> attributeToValue = new HashMap<>();

    public ItemAttributeData(ConfigurationSection configurationSection) {
        Attribute[] attributes = Attribute.values();
        for (Attribute attribute : attributes) {
            String attributeName = attribute.toString();
            if (attributeName.startsWith("GENERIC_")) {
                attributeName = attributeName.substring(8);
            }
            ConfigurationSection attributeSection = configurationSection.getConfigurationSection(attributeName);
            if (attributeSection != null) {
                double value = attributeSection.getDouble("value", 0);
                EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(attributeSection.getString("equipmentslot", "HAND"));
                AttributeModifier.Operation operation = AttributeModifier.Operation.valueOf(attributeSection.getString("operation", "ADD_NUMBER"));
                AttributeModifier attributeModifier = new AttributeModifier(
                        new NamespacedKey(ConfigurationData.getPlugin(), UUID.randomUUID().toString()),
                        value,
                        operation,
                        equipmentSlot.getGroup()
                );
                attributeToValue.put(attribute, attributeModifier);
            }
        }
    }

    public ItemAttributeData() {
    }

    public void applyAttributes(LivingEntity livingEntity) {
        for (Attribute attribute : Attribute.values()) {
            AttributeInstance instance = livingEntity.getAttribute(attribute);
            if (instance != null) {
                instance.setBaseValue(getValue(livingEntity, attribute));
            }
        }
    }

    public void applyAttributes(ItemMeta meta) {
        for (Map.Entry<Attribute, AttributeModifier> item : attributeToValue.entrySet()) {
            meta.addAttributeModifier(item.getKey(), item.getValue());
        }
    }

    public double getValue(LivingEntity livingEntity, Attribute attribute) {
        AttributeModifier attributeModifier = attributeToValue.get(attribute);
        if (attributeModifier == null) {
            return -1;
        }
        return attributeModifier.getAmount();
    }

}
