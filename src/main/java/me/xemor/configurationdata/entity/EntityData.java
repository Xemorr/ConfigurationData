package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.AttributeData;
import me.xemor.configurationdata.entity.attribute.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EntityData {
    protected final static LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    protected final EntityType entityType;
    protected final boolean shouldDespawn;
    private String nameTag;
    protected final AttributeData attributeData;
    protected EntityData passengerData;
    protected final List<EntityAttributeData> attributes = new ArrayList<>();

    protected EntityData(ConfigurationSection configurationSection) {
        entityType = EntityType.valueOf(configurationSection.getString("type", "ZOMBIE").toUpperCase());
        shouldDespawn = configurationSection.getBoolean("shouldDespawn", true);

        nameTag = configurationSection.getString("nametag");
        if (nameTag != null) {
            nameTag = LEGACY_SERIALIZER.serialize(MiniMessage.miniMessage().deserialize(nameTag));
        }
        
        ConfigurationSection attributeSection = configurationSection.getConfigurationSection("attributes");
        attributeData = attributeSection != null ? new AttributeData(attributeSection) : new AttributeData();
        
        ConfigurationSection passengerSection = configurationSection.getConfigurationSection("passenger");
        if (passengerSection != null) {
            passengerData = new EntityData(passengerSection);
        }

        Class<? extends Entity> entityClass = entityType.getEntityClass();
        if (entityClass == null) {
            return; // There is no realistic scenario in which that is null but, we check for it anyway.
        }

        if (Hoglin.class.isAssignableFrom(entityClass) || PiglinAbstract.class.isAssignableFrom(entityClass)) {
            attributes.add(new ZombifiableData(configurationSection));
        }
        if (Colorable.class.isAssignableFrom(entityClass)) {
            attributes.add(new ColorableData(configurationSection));
        }
        if (Slime.class.isAssignableFrom(entityClass) || Phantom.class.isAssignableFrom(entityClass)) {
            attributes.add(new SizeData(configurationSection));
        }
        if (Ageable.class.isAssignableFrom(entityClass)) {
            attributes.add(new BabyData(configurationSection));
        }
    }

    public EntityData() {
        entityType = EntityType.ZOMBIE;
        shouldDespawn = true;
        attributeData = new AttributeData();
    }

    public EntityData(EntityType entityType) {
        this.entityType = entityType;
        shouldDespawn = true;
        attributeData = new AttributeData();
    }

    public Entity spawnEntity(@NotNull World world, @NotNull Location location) {
        Entity entity = world.spawnEntity(location, entityType);
        applyAttributes(entity);
        attributes.forEach(attributeData -> attributeData.apply(entity));
        return entity;
    }

    public void applyAttributes(Entity entity) {
        if (nameTag != null) {
            entity.setCustomName(nameTag);
        }

        entity.setPersistent(!shouldDespawn);
        if (passengerData != null) {
            Entity passenger = passengerData.spawnEntity(entity.getWorld(), entity.getLocation());
            entity.addPassenger(passenger);
        }
    }

    public String getNameTag() {
        return nameTag;
    }

    public AttributeData getAttributeData() {
        return attributeData;
    }

    public static EntityData create(ConfigurationSection configurationSection) {
        String entityTypeRaw = configurationSection.getString("type", "ZOMBIE");
        EntityType entityType = EntityType.valueOf(entityTypeRaw.toUpperCase());

        EntityDataRegistry.EntityDataConstructor entityDataConstructor = EntityDataRegistry.getConstructor(entityType);
        return entityDataConstructor != null ? entityDataConstructor.apply(configurationSection) : null;
    }
}
