package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class BabyComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final boolean isBaby = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Ageable ageable = (Ageable) entity;
        if (isBaby) {
            ageable.setBaby();
        } else {
            ageable.setAdult();
        }
    }
}
