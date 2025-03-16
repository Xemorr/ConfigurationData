package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;

public class AbstractArrowComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final int knockbackStrength = 0;
    @JsonPropertyWithDefault
    private final double damage = 2.0;
    @JsonPropertyWithDefault
    private final int pierceLevel = 0;
    @JsonPropertyWithDefault
    private final boolean critical = false;
    @JsonPropertyWithDefault
    private final org.bukkit.entity.AbstractArrow.PickupStatus pickupStatus = org.bukkit.entity.AbstractArrow.PickupStatus.DISALLOWED;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        org.bukkit.entity.AbstractArrow abstractArrow = (org.bukkit.entity.AbstractArrow) entity;
        abstractArrow.setKnockbackStrength(knockbackStrength);
        abstractArrow.setDamage(damage);
        abstractArrow.setPierceLevel(pierceLevel);
        abstractArrow.setCritical(critical);
        abstractArrow.setPickupStatus(pickupStatus);
    }
}
