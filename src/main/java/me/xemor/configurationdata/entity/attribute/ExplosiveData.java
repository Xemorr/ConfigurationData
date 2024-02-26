package me.xemor.configurationdata.entity.attribute;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;

public class ExplosiveData extends EntityAttributeData {

    private final float yield;
    private final boolean isIncendiary;

    public ExplosiveData(ConfigurationSection configurationSection) {
        super(configurationSection);

        yield = (float) configurationSection.getDouble("yield", 2.5);
        isIncendiary = configurationSection.getBoolean("isIncendiary");
    }

    @Override
    public void apply(Entity entity) {
        Explosive explosive = (Explosive) entity;
        explosive.setYield(yield);
        explosive.setIsIncendiary(isIncendiary);
    }
}
