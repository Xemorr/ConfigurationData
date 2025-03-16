package me.xemor.configurationdata.deserializers.text;

import org.bukkit.potion.PotionEffectType;

import java.util.Locale;

public class PotionEffectTypeDeserializer extends TextDeserializer<PotionEffectType> {

    @Override
    public PotionEffectType deserialize(String text) {
        return PotionEffectType.getByName(text.toUpperCase(Locale.ROOT));
    }

}
