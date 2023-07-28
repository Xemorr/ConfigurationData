package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.AttributeData;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityData {

    private String nameTag;
    private final EntityType entityType;
    private final EquipmentData equipmentData;
    private final AttributeData attributeData;
    private EntityData passengerData;
    private boolean shouldDespawn;
    private List<ExtraData> extraData;
    private final static LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();


    public EntityData(ConfigurationSection configurationSection) {
        entityType = EntityType.valueOf(configurationSection.getString("type", "ZOMBIE").toUpperCase());
        nameTag = configurationSection.getString("nametag");
        shouldDespawn = configurationSection.getBoolean("shouldDespawn", true);
        if (nameTag != null) nameTag = legacySerializer.serialize(MiniMessage.miniMessage().deserialize(nameTag));
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

    private List<ExtraData> handleExtraData(ConfigurationSection extraSection) {
        Class<? extends Entity> entityClass = entityType.getEntityClass();
        List<ExtraData> extraData = new ArrayList<>();
        if (entityClass == null) return Collections.emptyList(); //there is no realistic scenario in which that is null: update, we check for it anyway.
        if (LivingEntity.class.isAssignableFrom(entityClass)) {
            extraData.add(new LivingEntityData(extraSection));
        }
        if (Hoglin.class.isAssignableFrom(entityClass) || PiglinAbstract.class.isAssignableFrom(entityClass)) {
            extraData.add(new ZombifiableData(extraSection));
        }
        if (Colorable.class.isAssignableFrom(entityClass)) {
            extraData.add(new ColorableData(extraSection));
        }
        if (AbstractHorse.class.isAssignableFrom(entityClass)) {
            extraData.add(new HorseData(extraSection));
        }
        if (Wolf.class.isAssignableFrom(entityClass)) {
            extraData.add(new WolfData(extraSection));
        }
        if (Slime.class.isAssignableFrom(entityClass) || Phantom.class.isAssignableFrom(entityClass)) {
            extraData.add(new SizeData(extraSection));
        }
        if (ThrownPotion.class.isAssignableFrom(entityClass)) {
            extraData.add(new PotionEntityData(extraSection));
        }
        if (Creeper.class.isAssignableFrom(entityClass)) {
            extraData.add(new CreeperData(extraSection));
        }
        if (Axolotl.class.isAssignableFrom(entityClass)) {
            extraData.add(new AxolotlData(extraSection));
        }
        if (Ageable.class.isAssignableFrom(entityClass)) {
            extraData.add(new BabyData(extraSection));
        }
        if (Item.class.isAssignableFrom(entityClass)) {
            extraData.add(new DroppedItemData(extraSection));
        }
        return extraData;
    }

    @NotNull
    public Entity createEntity(@NotNull World world, @NotNull Location location) {
        Entity entity = world.spawnEntity(location, entityType);
        if (nameTag != null) {
            entity.setCustomName(nameTag);
        }
        entity.setPersistent(!shouldDespawn);
        if (passengerData != null) {
            Entity passenger = passengerData.createEntity(world, location);
            entity.addPassenger(passenger);
        }
        if (extraData != null) {
            extraData.forEach(data -> data.applyData(entity));
        }
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setRemoveWhenFarAway(shouldDespawn);
            handleEquipment(livingEntity);
            attributeData.applyAttributes(livingEntity);
            livingEntity.setHealth(attributeData.getValue(livingEntity, Attribute.GENERIC_MAX_HEALTH));
        }
        return entity;
    }

    public void handleEquipment(LivingEntity livingEntity) {
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

    public AttributeData getAttributeData() {
        return attributeData;
    }

    public String getNameTag() {
        return nameTag;
    }

}
