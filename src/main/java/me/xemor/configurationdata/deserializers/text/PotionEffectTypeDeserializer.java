package me.xemor.configurationdata.deserializers.text;

import org.bukkit.potion.PotionEffectType;

import java.util.Locale;

public class PotionEffectTypeDeserializer extends TextDeserializer<PotionEffectType> {

    @Override
    public Deserialized<PotionEffectType> deserialize(String text) {
        PotionEffectType potionEffectType = PotionEffectType.getByName(text.toUpperCase(Locale.ROOT));
        if (potionEffectType == null) return new Deserialized<>(PotionEffectType.REGENERATION, true);
        return new Deserialized<>(potionEffectType, false);
    }

}
