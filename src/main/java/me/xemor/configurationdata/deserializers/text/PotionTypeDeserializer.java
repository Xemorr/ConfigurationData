package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.potion.PotionType;

public class PotionTypeDeserializer extends TextDeserializer<PotionType> {
    @Override
    public PotionType deserialize(String text) {
        return Registry.POTION.match(text);
    }
}
