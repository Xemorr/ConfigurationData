package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.xemor.configurationdata.EquipmentData;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class LivingEntityData extends EntityData {

    @JsonProperty
    private boolean canEquip;
    @JsonProperty
    private EquipmentData equipment;

    @Override
    public void applyExtraMetadata(Entity entity) {
        super.applyExtraMetadata(entity);
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.setRemoveWhenFarAway(shouldDespawn());
        livingEntity.setCanPickupItems(canEquip);
        equipment.applyEquipment(livingEntity);
        getAttributeData().applyAttributes("entity-setup", livingEntity);
        livingEntity.setHealth(getAttributeData().getValue(livingEntity, Attribute.MAX_HEALTH));
    }

    public EquipmentData getEquipment() {
        return equipment;
    }
}
