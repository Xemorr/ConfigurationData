package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface EntityComponent {
    void apply(Entity entity, EntityData builderSoFar);
}
