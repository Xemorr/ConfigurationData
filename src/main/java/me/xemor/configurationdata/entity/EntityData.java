package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.AttributeData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
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
            case WOLF: return new WolfData(extraSection);
            case PHANTOM:
            case MAGMA_CUBE:
            case SLIME: return new SizeData(extraSection);
            case ZOMBIE_HORSE:
            case SKELETON_HORSE:
            case DONKEY:
            case HORSE: return new HorseData(extraSection);
            case SPLASH_POTION: return new PotionEntityData(extraSection);
            case CREEPER: return new CreeperData(extraSection);
            case AXOLOTL: return new AxolotlData(extraSection);
            default: return null;
        }
    }

    @NotNull
    public Entity createEntity(@NotNull World world, @NotNull Location location) {
        Entity entity = world.spawnEntity(location, entityType);
        entity.setCustomName(nameTag);
        if (passengerData != null) {
            Entity passenger = passengerData.createEntity(world, location);
            entity.addPassenger(passenger);
        }
        if (extraData != null) {
            extraData.applyData(entity);
        }
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            handleEquipment(livingEntity);
            attributeData.applyAttributes(livingEntity);
            livingEntity.setHealth(attributeData.getValue(livingEntity, Attribute.GENERIC_MAX_HEALTH));
        }
        return entity;
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
