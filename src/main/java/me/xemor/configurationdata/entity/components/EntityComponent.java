package me.xemor.configurationdata.entity.components;

import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.entity.Entity;

public interface EntityComponent {
    void apply(Entity entity, NewEntityData builderSoFar);
}
