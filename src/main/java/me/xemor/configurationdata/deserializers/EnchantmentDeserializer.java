package me.xemor.configurationdata.deserializers;

import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

import java.util.Locale;

public class EnchantmentDeserializer extends TextDeserializer<Enchantment> {

    public Deserialized<Enchantment> deserialize(String text) {
        Enchantment enchantment = Registry.ENCHANTMENT.match(text.toUpperCase(Locale.ROOT));
        if (enchantment == null) {
            return new Deserialized<>(Registry.ENCHANTMENT.match("SHARPNESS"), true);
        }
        return new Deserialized<>(enchantment, false);
    }

}
