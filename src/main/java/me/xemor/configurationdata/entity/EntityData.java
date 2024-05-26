package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.AttributeData;
import me.xemor.configurationdata.ConfigurationData;
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

@SuppressWarnings("unused")
public class EntityData {
    protected final static LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    private final EntityType entityType;
    private final boolean shouldDespawn;
    private final String nameTag;
    private final boolean customNameVisible;
    private final boolean silent;
    private final boolean visualFire;
    private final AttributeData attributeData;
    private EntityData passengerData;
    private final List<EntityAttributeData> entitySpecificAttributes = new ArrayList<>();

    protected EntityData(ConfigurationSection configurationSection) {
        ConfigurationSection rootSection = configurationSection.getName().equals("extra") ? configurationSection.getParent() : configurationSection;

        entityType = EntityType.valueOf(rootSection.getString("type", "ZOMBIE").toUpperCase());
        shouldDespawn = rootSection.getBoolean("shouldDespawn", true);

        nameTag = rootSection.contains("nametag") ? LEGACY_SERIALIZER.serialize(MiniMessage.miniMessage().deserialize(rootSection.getString("nametag"))) : null;
        customNameVisible = rootSection.getBoolean("customNameVisible", true);
        silent = rootSection.getBoolean("silent");
        visualFire = rootSection.getBoolean("visualFire");
        
        ConfigurationSection attributeSection = rootSection.getConfigurationSection("attributes");
        attributeData = attributeSection != null ? new AttributeData(attributeSection, configurationSection.getCurrentPath()) : new AttributeData();
        
        ConfigurationSection passengerSection = rootSection.getConfigurationSection("passenger");
        if (passengerSection != null) {
            passengerData = new EntityData(passengerSection);
        }

        Class<? extends Entity> entityClass = entityType.getEntityClass();
        if (entityClass == null) {
            return; // There is no realistic scenario in which that is null but, we check for it anyway.
        }

        // Entity attributes are ordered alphabetically
        if (Ageable.class.isAssignableFrom(entityClass)) {
            entitySpecificAttributes.add(new BabyData(configurationSection));
        }
        if (Colorable.class.isAssignableFrom(entityClass)) {
            entitySpecificAttributes.add(new ColorableData(configurationSection));
        }
        if (Explosive.class.isAssignableFrom(entityClass)) {
            entitySpecificAttributes.add(new ExplosiveData(configurationSection));
        }
        if (Slime.class.isAssignableFrom(entityClass) || Phantom.class.isAssignableFrom(entityClass)) {
            entitySpecificAttributes.add(new SizeData(configurationSection));
        }
        if (ThrowableProjectile.class.isAssignableFrom(entityClass)) {
            entitySpecificAttributes.add(new ThrowableProjectileData(configurationSection));
        }
        if (Hoglin.class.isAssignableFrom(entityClass) || PiglinAbstract.class.isAssignableFrom(entityClass)) {
            entitySpecificAttributes.add(new ZombifiableData(configurationSection));
        }
    }

    private EntityData(EntityType entityType) {
        this.entityType = entityType;

        nameTag = null;
        shouldDespawn = true;
        customNameVisible = true;
        silent = false;
        visualFire = true;

        attributeData = new AttributeData();
    }

    public Entity spawnEntity(@NotNull Location location) {
        return spawnEntity(location.getWorld(), location);
    }

    public Entity spawnEntity(@NotNull World world, @NotNull Location location) {
        Entity entity = world.spawnEntity(location, entityType);
        applyAttributes(entity);
        entitySpecificAttributes.forEach(attributeData -> attributeData.apply(entity));
        return entity;
    }

    public void applyAttributes(Entity entity) {
        if (nameTag != null) {
            entity.setCustomName(nameTag);
        }

        entity.setCustomNameVisible(customNameVisible);
        entity.setPersistent(!shouldDespawn);
        entity.setSilent(silent);
        entity.setVisualFire(visualFire);

        if (passengerData != null) {
            Entity passenger = passengerData.spawnEntity(entity.getLocation());
            entity.addPassenger(passenger);
        }
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public boolean shouldDespawn() {
        return shouldDespawn;
    }

    public String getNameTag() {
        return nameTag;
    }

    public AttributeData getAttributeData() {
        return attributeData;
    }

    public List<EntityAttributeData> getEntitySpecificAttributes() {
        return entitySpecificAttributes;
    }

    public static EntityData create(EntityType entityType) {
        return new EntityData(entityType);
    }

    public static EntityData create(ConfigurationSection configurationSection) {
        return create(configurationSection, EntityType.ZOMBIE);
    }

    public static EntityData create(ConfigurationSection parentSection, String path, @NotNull EntityType def) {
        if (parentSection.isString(path)) {
            return EntityData.create(EntityType.valueOf(parentSection.getString(path)));
        } else {
            ConfigurationSection entitySection = parentSection.getConfigurationSection(path);
            return entitySection != null ? create(parentSection, def) : EntityData.create(def);
        }
    }

    public static EntityData create(ConfigurationSection configurationSection, @NotNull EntityType def) {
        String entityTypeRaw = configurationSection.getString("type");
        EntityType entityType = entityTypeRaw != null ? EntityType.valueOf(entityTypeRaw.toUpperCase()) : def;

        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecated: The contents of the 'extra' section at '" + configurationSection.getCurrentPath() + "' should now be placed in the root of the entity section");
        }

        EntityDataRegistry.EntityDataConstructor entityDataConstructor = EntityDataRegistry.getConstructor(entityType);
        return entityDataConstructor != null ? entityDataConstructor.apply(configurationSection) : null;
    }
}
