package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.EquipmentData;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

public class LivingEntityData extends EntityData {

    private final boolean canEquip;

    private final EquipmentData equipmentData;

    public LivingEntityData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecated: The contents of the 'extra' section at '" + configurationSection.getCurrentPath() + "' should now be placed in the root of the entity section");
        }

        canEquip = configurationSection.getBoolean("canEquip", false);

        ConfigurationSection equipmentSection = configurationSection.getConfigurationSection("equipment");
        equipmentData = equipmentSection != null ? new EquipmentData(equipmentSection) : new EquipmentData();
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.setRemoveWhenFarAway(shouldDespawn);
        livingEntity.setCanPickupItems(canEquip);
        applyEquipment(livingEntity);
        attributeData.applyAttributes(livingEntity);
        livingEntity.setHealth(attributeData.getValue(livingEntity, Attribute.GENERIC_MAX_HEALTH));
    }

    public final void applyEquipment(LivingEntity livingEntity) {
        EntityEquipment equipment = livingEntity.getEquipment();
        if (equipment == null) return;
        equipment.setHelmet(equipmentData.getHelmet(), true);
        equipment.setHelmetDropChance(equipmentData.getHelmetDropRate());
        equipment.setChestplate(equipmentData.getChestplate(), true);
        equipment.setChestplateDropChance(equipmentData.getChestplateDropRate());
        equipment.setLeggings(equipmentData.getLeggings(), true);
        equipment.setLeggingsDropChance(equipmentData.getLeggingsDropRate());
        equipment.setBoots(equipmentData.getBoots(), true);
        equipment.setBootsDropChance(equipmentData.getBootsDropRate());
        equipment.setItemInMainHand(equipmentData.getMainHand(), true);
        equipment.setItemInMainHandDropChance(equipmentData.getMainHandDropRate());
        equipment.setItemInOffHand(equipmentData.getOffHand(), true);
        equipment.setItemInOffHandDropChance(equipmentData.getOffHandDropRate());
    }

    public EquipmentData getEquipment() {
        return equipmentData;
    }
}
