package me.xemor.configurationdata.entity.components;

import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.DyeColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.material.Colorable;

public class ColorableComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private DyeColor dyeColor = null;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Colorable colorable = (org.bukkit.material.Colorable) entity;
        if (dyeColor != null) colorable.setColor(dyeColor);
    }
}
