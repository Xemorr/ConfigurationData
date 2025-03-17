package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.material.Colorable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorableComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private DyeColor dyeColor = null;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Colorable colorable = (Colorable) entity;
        if (dyeColor != null) colorable.setColor(dyeColor);
    }
}
