package me.xemor.configurationdata.entity.attribute;

import me.xemor.configurationdata.ItemStackData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrowableProjectile;

public class ThrowableProjectileData extends EntityAttributeData {

    private final ItemStackData itemStackData;

    public ThrowableProjectileData(ConfigurationSection configurationSection) {
        super(configurationSection);

        itemStackData = configurationSection.contains("item") ? new ItemStackData(configurationSection.getConfigurationSection("item")) : null;
    }

    @Override
    public void apply(Entity entity) {
        ThrowableProjectile throwableProjectile = (ThrowableProjectile) entity;
        if (itemStackData != null) {
            throwableProjectile.setItem(itemStackData.item());
        }
    }
}
