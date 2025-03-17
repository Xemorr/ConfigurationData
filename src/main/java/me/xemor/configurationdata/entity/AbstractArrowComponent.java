package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractArrowComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private int knockbackStrength = 0;
    @JsonPropertyWithDefault
    private double damage = 2.0;
    @JsonPropertyWithDefault
    private int pierceLevel = 0;
    @JsonPropertyWithDefault
    private boolean critical = false;
    @JsonPropertyWithDefault
    private org.bukkit.entity.AbstractArrow.PickupStatus pickupStatus = org.bukkit.entity.AbstractArrow.PickupStatus.DISALLOWED;

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
