package me.xemor.configurationdata;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.w3c.dom.Attr;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class AttributeData {

    EnumMap<Attribute, AttributeModifier> attributeToValue = new EnumMap<>(Attribute.class);

    public AttributeData(ConfigurationSection configurationSection) {
        Attribute[] attributes = Attribute.values();
        for (Attribute attribute : attributes) {
            String attributeName = attribute.toString();
            if (attributeName.startsWith("GENERIC_")) {
                attributeName = attributeName.substring(8);
            }
            String name = configurationSection.getCurrentPath() + attributeName;
            AttributeModifier modifier = new AttributeModifier(new UUID(name.hashCode(), name.hashCode()), name, configurationSection.getDouble(attributeName, -1), AttributeModifier.Operation.ADD_NUMBER)
            attributeToValue.put(attribute, modifier);
        }
    }

    public AttributeData() {
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
                instance.removeModifier(pair.getValue());
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
