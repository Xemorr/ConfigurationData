package me.xemor.configurationdata.deserializers;

import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;

public class AttributeDeserializer extends TextDeserializer<Attribute> {
    @Override
    public Deserialized<Attribute> deserialize(String text) {
        Attribute attribute = Registry.ATTRIBUTE.match(text);
        if (attribute == null) Registry.ATTRIBUTE.match("GENERIC_" + text);
        if (attribute == null) return new Deserialized<>(Attribute.ARMOR, true);
        return new Deserialized<>(attribute, false);
    }
}
