package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SpectralArrow;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpectralArrowComponent extends AbstractArrowComponent {

    @JsonPropertyWithDefault
    private int glowingTicks = 200;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        SpectralArrow spectralArrow = (SpectralArrow) entity;
        spectralArrow.setGlowingTicks(glowingTicks);
    }
}
