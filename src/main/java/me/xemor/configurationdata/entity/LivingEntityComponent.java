package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.EquipmentData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivingEntityComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private boolean canEquip = false;
    @JsonPropertyWithDefault
    private EquipmentData equipment = null;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.setRemoveWhenFarAway(builderSoFar.shouldDespawn());
        livingEntity.setCanPickupItems(canEquip);
        if (equipment != null) equipment.applyEquipment(livingEntity);
        for (Map.Entry<Attribute, Double> entry : builderSoFar.getAttributes().entrySet()) {
            livingEntity.getAttribute(entry.getKey()).setBaseValue(entry.getValue());
        }
        livingEntity.setHealth(livingEntity.getAttribute(Attribute.MAX_HEALTH).getValue());
    }

    public EquipmentData getEquipment() {
        return equipment;
    }
}
