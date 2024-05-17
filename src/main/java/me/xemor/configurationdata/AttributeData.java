package me.xemor.configurationdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class AttributeData {

    private final EnumMap<Attribute, AttributeModifier> attributeToValue = new EnumMap<>(Attribute.class);
    private final String name;

    public AttributeData(ConfigurationSection configurationSection, @NotNull String name) {
        Attribute[] attributes = Attribute.values();
        this.name = name;
        for (Attribute attribute : attributes) {
            String attributeName = attribute.toString();
            if (attributeName.startsWith("GENERIC_")) {
                attributeName = attributeName.substring(8);
            }
            double value = configurationSection.getDouble(attributeName, -1);
            if (value == -1) continue;
            AttributeModifier modifier = new AttributeModifier(new UUID(name.hashCode(), name.hashCode()), name, value, AttributeModifier.Operation.ADD_NUMBER);
            attributeToValue.put(attribute, modifier);
        }
    }

    public AttributeData() {
        name = "default";
    }

    public void applyAttributes(LivingEntity livingEntity) {
        for (Map.Entry<Attribute, AttributeModifier> pair : this.attributeToValue.entrySet()) {
            AttributeInstance instance = livingEntity.getAttribute(pair.getKey());
            if (instance != null) {
                AttributeModifier modifier = new AttributeModifier(pair.getValue().getUniqueId(), pair.getValue().getName(), pair.getValue().getAmount() - instance.getBaseValue(), AttributeModifier.Operation.ADD_NUMBER);
                instance.addModifier(modifier);
            }
        }
    }

    public void resetAttributes(LivingEntity livingEntity) {
        for (Map.Entry<Attribute, AttributeModifier> pair : this.attributeToValue.entrySet()) {
            AttributeInstance instance = livingEntity.getAttribute(pair.getKey());
            if (instance != null) {
                for (AttributeModifier modifier : instance.getModifiers()) {
                    if (this.name.equals(modifier.getName())) {
                        instance.removeModifier(modifier);
                    }
                }
            }
        }
    }

    public double getValue(LivingEntity livingEntity, Attribute attribute) {
        AttributeModifier value = attributeToValue.get(attribute);
        if (value == null) {
            return livingEntity.getAttribute(attribute).getBaseValue();
        }
        return value.getAmount();
    }

}
