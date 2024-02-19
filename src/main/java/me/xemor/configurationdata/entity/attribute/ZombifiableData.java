package me.xemor.configurationdata.entity.attribute;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.PiglinAbstract;

public class ZombifiableData extends EntityAttributeData {

    private final boolean immuneToZombification;

    public ZombifiableData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        immuneToZombification = configurationSection.getBoolean("isZombifiable", false);
    }

    @Override
    public void apply(Entity entity) {
        if (entity instanceof PiglinAbstract) {
            PiglinAbstract piglinAbstract = (PiglinAbstract) entity;
            piglinAbstract.setImmuneToZombification(immuneToZombification);
        }
        else if (entity instanceof Hoglin) {
            Hoglin hoglin = (Hoglin) entity;
            hoglin.setImmuneToZombification(immuneToZombification);
        }
    }
}
