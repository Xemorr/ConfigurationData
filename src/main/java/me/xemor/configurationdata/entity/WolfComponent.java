package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WolfComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private boolean angry = false;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Wolf wolf = (Wolf) entity;
        wolf.setAngry(angry);
    }
}
