package me.xemor.configurationdata.entity.components;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;

public class ExplosiveComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private float yield;
    @JsonPropertyWithDefault
    private boolean isIncendiary = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Explosive explosive = (Explosive) entity;
        explosive.setYield(yield);
        explosive.setIsIncendiary(isIncendiary);
    }
}
