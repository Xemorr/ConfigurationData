package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgeableComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private boolean isBaby = false;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Ageable ageable = (Ageable) entity;
        if (isBaby) {
            ageable.setBaby();
        } else {
            ageable.setAdult();
        }
    }
}
