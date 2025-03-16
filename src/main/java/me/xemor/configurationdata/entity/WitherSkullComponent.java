package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WitherSkull;

public class WitherSkullComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final boolean isCharged = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        WitherSkull witherSkull = (WitherSkull) entity;
        witherSkull.setCharged(isCharged);
    }
}
