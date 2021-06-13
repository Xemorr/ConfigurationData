package me.xemor.configurationdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;

import java.util.EnumMap;

public class AttributeData {

    EnumMap<Attribute, Double> attributeToValue = new EnumMap<>(Attribute.class);

    public AttributeData(ConfigurationSection configurationSection) {
        Attribute[] attributes = Attribute.values();
        for (Attribute attribute : attributes) {
            String attributeName = attribute.toString();
            if (attributeName.startsWith("GENERIC_")) {
                attributeName = attributeName.substring(8);
            }
            attributeToValue.put(attribute, configurationSection.getDouble(attributeName, -1));
        }
    }

    public AttributeData() {
    }

    public void applyAttributes(LivingEntity livingEntity) {
        for (Attribute attribute : Attribute.values()) {
            AttributeInstance instance = livingEntity.getAttribute(attribute);
            if (instance != null) {
                instance.setBaseValue(getValue(livingEntity, attribute));
            }
        }
    }

    public double getValue(LivingEntity livingEntity, Attribute attribute) {
        double value = attributeToValue.getOrDefault(attribute, -1D);
        if (value == -1) {
            return livingEntity.getAttribute(attribute).getBaseValue();
        }
        return value;
    }

}
