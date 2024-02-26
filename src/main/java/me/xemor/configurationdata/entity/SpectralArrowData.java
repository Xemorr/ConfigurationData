package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SpectralArrow;

public class SpectralArrowData extends AbstractArrowData {

    private final int glowingTicks;

    public SpectralArrowData(ConfigurationSection configurationSection) {
        super(configurationSection);

        glowingTicks = configurationSection.getInt("glowingTicks", 200);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        SpectralArrow spectralArrow = (SpectralArrow) entity;
        spectralArrow.setGlowingTicks(glowingTicks);
    }
}
