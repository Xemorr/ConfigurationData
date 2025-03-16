package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;

public class LlamaComponent extends HorseComponent {

    private final Llama.Color color;
    private final int strength;

    public LlamaComponent(ConfigurationSection configurationSection) {
        super(configurationSection);

        color = configurationSection.contains("color") ? Llama.Color.valueOf(configurationSection.getString("color")) : null;
        strength = configurationSection.getInt("strength", 1);
    }

    @Override
    public void applyExtraMetadata(Entity entity) {
        super.applyExtraMetadata(entity);

        Llama llama = (Llama) entity;
        if (color != null) {
            llama.setColor(color);
        }
        llama.setStrength(strength);
    }
}
