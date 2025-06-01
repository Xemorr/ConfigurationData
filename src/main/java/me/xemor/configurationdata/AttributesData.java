package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("attributes")
    private Map<Attribute, Double> attributes = new HashMap<>();
    @JsonPropertyWithDefault
    private EquipmentSlotGroup equipmentSlot = EquipmentSlotGroup.ANY;
    @JsonPropertyWithDefault
    private Operation operation = Operation.MULTIPLY_SCALAR;
    @JsonProperty
    @JsonAlias("unique_key")
    private String uniqueKey;

    public AttributesData() {}

    @JsonIgnore
    public AttributesData(Map<Attribute, Double> attributes, EquipmentSlotGroup equipmentSlot, Operation operation, String uniqueKey) {
        this.attributes = attributes;
        if (equipmentSlot != null) this.equipmentSlot = equipmentSlot;
        if (operation != null) this.operation = operation;
        this.uniqueKey = uniqueKey;
    }

    public void applyAttributes(LivingEntity livingEntity) {
        for (Map.Entry<Attribute, Double> pair : attributes.entrySet()) {
            if (pair.getKey() == null) {
                ConfigurationData.getLogger().severe("null key in attributes when applying");
                ConfigurationData.getLogger().severe(
                        "valid attributes are: %s".formatted(
                        Registry.ATTRIBUTE
                                .stream()
                                .map((attr) -> attr.getKey().getKey())
                                .reduce((s1, s2) -> s1 + ", " + s2))
                );
                continue;
            }
            if (pair.getValue() == null) {
                ConfigurationData.getLogger().severe("null value in attributes when applying");
            }
            double value = pair.getValue();
            if (operation == Operation.MULTIPLY_SCALAR) { // hack so you can actually have multiplies without silly mojang one
                value = value - 1;
            }
            AttributeInstance instance = livingEntity.getAttribute(pair.getKey());
            if (instance != null) {
                AttributeModifier modifier;
                if (getMinorVersion() <= 20) {
                    modifier = new AttributeModifier(new UUID(uniqueKey.hashCode(), uniqueKey.hashCode()), uniqueKey, value, operation.getAttributeModifierOperation());
                }
                else {
                    modifier = new AttributeModifier(new NamespacedKey(ConfigurationData.getPlugin(), uniqueKey), value, operation.getAttributeModifierOperation(), equipmentSlot);
                }
                instance.addModifier(modifier);
            }
        }
    }

    public void applyAttributes(ItemMeta itemMeta) {
        for (Map.Entry<Attribute, Double> pair : attributes.entrySet()) {
            double value = pair.getValue();
            if (operation == Operation.MULTIPLY_SCALAR) {
                value = value - 1;
            }
            if (getMinorVersion() <= 20) {
                itemMeta.addAttributeModifier(
                    pair.getKey(),
                    new AttributeModifier(
                            new UUID(uniqueKey.hashCode(), uniqueKey.hashCode()),
                            uniqueKey,
                            value,
                            operation.getAttributeModifierOperation()
                    )
                );
            }
            else {
                itemMeta.addAttributeModifier(
                        pair.getKey(),
                        new AttributeModifier(
                                new NamespacedKey(ConfigurationData.getPlugin(), uniqueKey),
                                value,
                                operation.getAttributeModifierOperation(),
                                equipmentSlot
                        )
                );
            }
        }
    }

    public int getMinorVersion() {
        return Integer.parseInt(Bukkit.getServer().getBukkitVersion().split("-")[0].split("\\.")[1]);
    }

    public void resetAttributes(LivingEntity livingEntity) {
        for (Map.Entry<Attribute, Double> pair : attributes.entrySet()) {
            AttributeInstance instance = livingEntity.getAttribute(pair.getKey());
            if (instance != null) {
                for (AttributeModifier modifier : instance.getModifiers()) {
                    if (getMinorVersion() <= 20) {
                        if (uniqueKey.equals(modifier.getName())) {
                            instance.removeModifier(modifier);
                        }
                    }
                    else {
                        if (uniqueKey.equalsIgnoreCase(modifier.getKey().getKey())) {
                            instance.removeModifier(modifier);
                        }
                    }
                }
            }
        }
    }

    public double getValue(LivingEntity livingEntity, Attribute attribute) {
        Double value = attributes.get(attribute);
        if (value == null) {
            return livingEntity.getAttribute(attribute).getBaseValue();
        }
        return value;
    }

    public enum Operation {

        /**
         * Adds (or subtracts) the specified amount to the base value.
         */
        ADD_NUMBER,
        /**
         * Adds this scalar of amount to the base value.
         */
        ADD_SCALAR,
        /**
         * Multiply amount by this value, after adding 1 to it.
         */
        MULTIPLY_SCALAR_1,
        /**
         * Multiply amount by this value
         */
        MULTIPLY_SCALAR;

        public AttributeModifier.Operation getAttributeModifierOperation() {
            return switch (this) {
                case ADD_NUMBER -> AttributeModifier.Operation.ADD_NUMBER;
                case ADD_SCALAR -> AttributeModifier.Operation.ADD_SCALAR;
                case MULTIPLY_SCALAR_1, MULTIPLY_SCALAR -> AttributeModifier.Operation.MULTIPLY_SCALAR_1;
            };
        }
    }

}
