package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.AttributeData;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public abstract class NewEntityData {
    private final static LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    protected final EntityType entityType;
    protected final boolean shouldDespawn;
    private String nameTag;

    protected final AttributeData attributeData;
    private EntityData passengerData;

    public NewEntityData(ConfigurationSection configurationSection) {
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
    }

    public Entity spawnEntity(@NotNull World world, @NotNull Location location) {
        Entity entity = world.spawnEntity(location, entityType);
        applyAttributes(entity);
        return entity;
    }

    public void applyAttributes(Entity entity) {
        if (nameTag != null) {
            entity.setCustomName(nameTag);
        }

        entity.setPersistent(!shouldDespawn);
        if (passengerData != null) {
            Entity passenger = passengerData.createEntity(entity.getWorld(), entity.getLocation());
            entity.addPassenger(passenger);
        }
    }

    public String getNameTag() {
        return nameTag;
    }

    public AttributeData getAttributeData() {
        return attributeData;
    }
}
