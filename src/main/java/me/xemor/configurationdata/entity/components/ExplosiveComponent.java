package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExplosiveComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private float yield;
    @JsonPropertyWithDefault
    private boolean isIncendiary = false;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Explosive explosive = (Explosive) entity;
        explosive.setYield(yield);
        explosive.setIsIncendiary(isIncendiary);
    }
}
