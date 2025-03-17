package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreeperComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private int fuse = 30;
    @JsonPropertyWithDefault
    private boolean ignite = false;
    @JsonPropertyWithDefault
    private int explosionRadius = 3;
    @JsonPropertyWithDefault
    private boolean powered = false;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Creeper creeper = (Creeper) entity;
        creeper.setMaxFuseTicks(fuse);
        creeper.setPowered(powered);
        creeper.setExplosionRadius(explosionRadius);
        if (ignite) creeper.ignite();
    }
}
