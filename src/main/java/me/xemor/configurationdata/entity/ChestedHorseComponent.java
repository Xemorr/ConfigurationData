package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChestedHorseComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private boolean hasChest = false;
    @JsonPropertyWithDefault

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        ChestedHorse chestedHorse = (ChestedHorse) entity;
        chestedHorse.setCarryingChest(hasChest);
    }
}
