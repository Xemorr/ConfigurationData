package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;

public class LlamaData extends HorseData {

    private final Llama.Color color;
    private final int strength;

    public LlamaData(ConfigurationSection configurationSection) {
        super(configurationSection);

        color = configurationSection.contains("color") ? Llama.Color.valueOf(configurationSection.getString("color")) : null;
        strength = configurationSection.getInt("strength", 1);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Llama llama = (Llama) entity;
        if (color != null) {
            llama.setColor(color);
        }
        llama.setStrength(strength);
    }
}
