package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.*;
import me.xemor.configurationdata.deserializers.text.RegistryDeserializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AttributesData {
    private final Map<Attribute, Double> attributeToValue = new HashMap<>();
    @JsonIgnore
    private final static RegistryDeserializer<Attribute> attributeDeserializer = new RegistryDeserializer<>(Registry.ATTRIBUTE);

    @JsonPropertyWithDefault
    private EquipmentSlotGroup equipmentSlot = EquipmentSlotGroup.ANY;
    @JsonPropertyWithDefault
    private AttributeModifier.Operation operation = AttributeModifier.Operation.ADD_NUMBER;

    @JsonAnySetter
    public void addAttribute(String attributeStr, double value) {
        Attribute attribute = attributeDeserializer.parse(attributeStr);
        if (attribute == null) return;
        attributeToValue.put(attribute, value);
    }

    public AttributesData() {}

    public void applyAttributes(String uniqueConsistentString, LivingEntity livingEntity) {
        for (Map.Entry<Attribute, Double> pair : this.attributeToValue.entrySet()) {
            AttributeInstance instance = livingEntity.getAttribute(pair.getKey());
            if (instance != null) {
                AttributeModifier modifier;
                if (getMinorVersion() <= 20) {
                    modifier = new AttributeModifier(new UUID(uniqueConsistentString.hashCode(), uniqueConsistentString.hashCode()), uniqueConsistentString, pair.getValue(), operation);
                }
                else {
                    modifier = new AttributeModifier(new NamespacedKey(ConfigurationData.getPlugin(), uniqueConsistentString), pair.getValue(), operation, equipmentSlot);
                }
                instance.addModifier(modifier);
            }
        }
    }

    public void applyAttributes(String uniqueConsistentString, ItemMeta itemMeta) {
        for (Map.Entry<Attribute, Double> pair : this.attributeToValue.entrySet()) {
            if (getMinorVersion() <= 20) {
                itemMeta.addAttributeModifier(
                    pair.getKey(),
                    new AttributeModifier(
                            new UUID(uniqueConsistentString.hashCode(), uniqueConsistentString.hashCode()),
                            uniqueConsistentString,
                            pair.getValue(),
                            operation
                    )
                );
            }
            else {
                itemMeta.addAttributeModifier(
                        pair.getKey(),
                        new AttributeModifier(
                                new NamespacedKey(ConfigurationData.getPlugin(), uniqueConsistentString),
                                pair.getValue(),
                                operation,
                                equipmentSlot
                        )
                );
            }
        }
    }

    public int getMinorVersion() {
        return Integer.parseInt(Bukkit.getServer().getBukkitVersion().split("-")[0].split("\\.")[1]);
    }

    public void resetAttributes(String uniqueConsistentString, LivingEntity livingEntity) {
        for (Map.Entry<Attribute, Double> pair : this.attributeToValue.entrySet()) {
            AttributeInstance instance = livingEntity.getAttribute(pair.getKey());
            if (instance != null) {
                for (AttributeModifier modifier : instance.getModifiers()) {
                    if (getMinorVersion() <= 20) {
                        if (uniqueConsistentString.equals(modifier.getName())) {
                            instance.removeModifier(modifier);
                        }
                    }
                    else {
                        if (uniqueConsistentString.equalsIgnoreCase(modifier.getKey().getKey())) {
                            instance.removeModifier(modifier);
                        }
                    }
                }
            }
        }
    }

    public double getValue(LivingEntity livingEntity, Attribute attribute) {
        Double value = attributeToValue.get(attribute);
        if (value == null) {
            return livingEntity.getAttribute(attribute).getBaseValue();
        }
        return value;
    }

}
