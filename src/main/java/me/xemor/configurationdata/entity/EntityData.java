package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.AttributeData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.jetbrains.annotations.NotNull;

public class EntityData {

    String nameTag;
    EntityType entityType;
    EquipmentData equipmentData;
    AttributeData attributeData;
    EntityData passengerData;
    ExtraData extraData;

    public EntityData(ConfigurationSection configurationSection) {
        entityType = EntityType.valueOf(configurationSection.getString("type", "ZOMBIE").toUpperCase());
        nameTag = configurationSection.getString("nametag");
        if (nameTag != null) nameTag = ChatColor.translateAlternateColorCodes('&', nameTag);
        ConfigurationSection equipmentSection = configurationSection.getConfigurationSection("equipment");
        if (equipmentSection == null) equipmentData = new EquipmentData();
        else equipmentData = new EquipmentData(equipmentSection);
        ConfigurationSection attributeSection = configurationSection.getConfigurationSection("attributes");
        if (attributeSection == null) attributeData = new AttributeData();
        else attributeData = new AttributeData(attributeSection);
        ConfigurationSection passengerSection = configurationSection.getConfigurationSection("passenger");
        if (passengerSection != null) {
            passengerData = new EntityData(passengerSection);
        }
        ConfigurationSection extraSection = configurationSection.getConfigurationSection("extra");
        if (extraSection != null) {
            extraData = handleExtraData(extraSection);
        }
    }

    public EntityData() {
        entityType = EntityType.ZOMBIE;
        equipmentData = new EquipmentData();
        attributeData = new AttributeData();
    }

    private ExtraData handleExtraData(ConfigurationSection extraSection) {
        switch (entityType) {
            case WOLF: new WolfData(extraSection);
            case PHANTOM:
            case MAGMA_CUBE:
            case SLIME: new SizeData(extraSection);
            case ZOMBIE_HORSE:
            case SKELETON_HORSE:
            case DONKEY:
            case HORSE: new HorseData(extraSection);
            default: return null;
        }
    }

    @NotNull
    public LivingEntity createEntity(@NotNull World world, @NotNull Location location) {
        LivingEntity livingEntity = (LivingEntity) world.spawnEntity(location, entityType);
        livingEntity.setCustomName(nameTag);
        handleEquipment(livingEntity);
        if (passengerData != null) {
            LivingEntity passenger = passengerData.createEntity(world, location);
            livingEntity.addPassenger(passenger);
        }
        if (extraData != null) {
            extraData.applyData(livingEntity);
        }
        attributeData.applyAttributes(livingEntity);
        livingEntity.setHealth(attributeData.getValue(livingEntity, Attribute.GENERIC_MAX_HEALTH));
        return livingEntity;
    }

    public void handleEquipment(LivingEntity livingEntity) {
        EntityEquipment equipment = livingEntity.getEquipment();
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

    public AttributeData getAttributeData() {
        return attributeData;
    }

    public String getNameTag() {
        return nameTag;
    }

}
