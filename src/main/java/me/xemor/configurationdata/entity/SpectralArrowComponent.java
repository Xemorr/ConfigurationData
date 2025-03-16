package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SpectralArrow;

public class SpectralArrowComponent extends AbstractArrowComponent {

    @JsonPropertyWithDefault
    private final int glowingTicks = 200;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        SpectralArrow spectralArrow = (SpectralArrow) entity;
        spectralArrow.setGlowingTicks(glowingTicks);
    }
}
