package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;

public class CreeperComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final int fuse = 30;
    @JsonPropertyWithDefault
    private final boolean ignite = false;
    @JsonPropertyWithDefault
    private final int explosionRadius = 3;
    @JsonPropertyWithDefault
    private final boolean powered = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Creeper creeper = (Creeper) entity;
        creeper.setMaxFuseTicks(fuse);
        creeper.setPowered(powered);
        creeper.setExplosionRadius(explosionRadius);
        if (ignite) creeper.ignite();
    }
}
