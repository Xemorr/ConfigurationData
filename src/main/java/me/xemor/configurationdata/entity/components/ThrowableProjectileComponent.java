package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.ItemStackData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrowableProjectile;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThrowableProjectileComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private ItemStackData itemStackData = null;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        ThrowableProjectile throwableProjectile = (ThrowableProjectile) entity;
        if (itemStackData != null) {
            throwableProjectile.setItem(itemStackData.item());
        }
    }
}
