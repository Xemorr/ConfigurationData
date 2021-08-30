package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.PiglinAbstract;

public class ZombifiableData extends ExtraData {

    private boolean immuneToZombification;

    public ZombifiableData(ConfigurationSection configurationSection) {
        super(configurationSection);
        immuneToZombification = !configurationSection.getBoolean("isZombifiable", true);
    }

    @Override
    public void applyData(Entity entity) {
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
