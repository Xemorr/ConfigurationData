package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

import java.util.Locale;

public class EnchantmentDeserializer extends TextDeserializer<Enchantment> {

    public Enchantment deserialize(String text) {
        return Registry.ENCHANTMENT.match(text.toUpperCase(Locale.ROOT));
    }

}
