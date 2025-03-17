package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WitherSkull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WitherSkullComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private boolean isCharged = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        WitherSkull witherSkull = (WitherSkull) entity;
        witherSkull.setCharged(isCharged);
    }
}
