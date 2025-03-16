package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;

public class AttributeDeserializer extends TextDeserializer<Attribute> {
    @Override
    public Attribute deserialize(String text) {
        Attribute attribute = Registry.ATTRIBUTE.match(text);
        if (attribute == null) attribute = Registry.ATTRIBUTE.match("GENERIC_" + text);
        return attribute;
    }
}
