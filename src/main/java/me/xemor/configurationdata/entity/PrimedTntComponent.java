package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimedTntComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private int fuseTicks = 100;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        TNTPrimed primedTnt = (TNTPrimed) entity;
        primedTnt.setFuseTicks(fuseTicks);
    }
}
