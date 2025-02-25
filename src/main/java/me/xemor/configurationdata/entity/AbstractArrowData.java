package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;

public class AbstractArrowData extends EntityData {

    private final int knockbackStrength;
    private final double damage;
    private final int pierceLevel;
    private final boolean critical;
    private final AbstractArrow.PickupStatus pickupStatus;


    public AbstractArrowData(ConfigurationSection configurationSection) {
        super(configurationSection);

        knockbackStrength = configurationSection.getInt("knockbackStrength", 0);
        damage = configurationSection.getDouble("damage", 2.0);
        pierceLevel = configurationSection.getInt("pierceLevel", 0);
        critical = configurationSection.getBoolean("critical");
        pickupStatus = AbstractArrow.PickupStatus.valueOf(configurationSection.getString("pickupStatus", "DISALLOWED"));
    }

    @Override
    public void applyExtraMetadata(Entity entity) {
        super.applyExtraMetadata(entity);

        AbstractArrow abstractArrow = (AbstractArrow) entity;
        abstractArrow.setKnockbackStrength(knockbackStrength);
        abstractArrow.setDamage(damage);
        abstractArrow.setPierceLevel(pierceLevel);
        abstractArrow.setCritical(critical);
        abstractArrow.setPickupStatus(pickupStatus);
    }
}
