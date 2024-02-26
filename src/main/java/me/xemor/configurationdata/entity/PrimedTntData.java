package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

public class PrimedTntData extends EntityData {

    private final int fuseTicks;

    public PrimedTntData(ConfigurationSection configurationSection) {
        super(configurationSection);

        fuseTicks = configurationSection.getInt("fuseTicks", 100);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        TNTPrimed primedTnt = (TNTPrimed) entity;
        primedTnt.setFuseTicks(fuseTicks);
    }
}
