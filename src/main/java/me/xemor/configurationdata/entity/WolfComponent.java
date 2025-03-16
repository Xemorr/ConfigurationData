package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;

public class WolfComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final boolean angry = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Wolf wolf = (Wolf) entity;
        wolf.setAngry(angry);
    }
}
