package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;

public class CreeperData extends LivingEntityData {

    private final int fuse;
    private final boolean ignite;
    private final int explosionRadius;
    private final boolean powered;

    public CreeperData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecated: The contents of the 'extra' section at '" + configurationSection.getCurrentPath() + "' should now be placed in the root of the entity section");
        }

        fuse = configurationSection.getInt("fuse", 30);
        ignite = configurationSection.getBoolean("ignited", false);
        explosionRadius = configurationSection.getInt("explosionRadius", 3);
        powered = configurationSection.getBoolean("powered");
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Creeper creeper = (Creeper) entity;
        creeper.setMaxFuseTicks(fuse);
        creeper.setPowered(powered);
        creeper.setExplosionRadius(explosionRadius);
        if (ignite) creeper.ignite();
    }
}
