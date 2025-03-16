package me.xemor.configurationdata.entity.components;

import me.xemor.configurationdata.ItemStackData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrowableProjectile;

public class ThrowableProjectileComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final ItemStackData itemStackData = null;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        ThrowableProjectile throwableProjectile = (ThrowableProjectile) entity;
        if (itemStackData != null) {
            throwableProjectile.setItem(itemStackData.item());
        }
    }
}
