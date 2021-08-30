package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class LivingEntityData extends ExtraData {

    private boolean canEquip;

    public LivingEntityData(ConfigurationSection configurationSection) {
        super(configurationSection);
        canEquip = configurationSection.getBoolean("canEquip", false);
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setCanPickupItems(canEquip);
        }
    }
}
