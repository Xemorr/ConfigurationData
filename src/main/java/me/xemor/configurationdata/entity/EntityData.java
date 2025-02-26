package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.xemor.configurationdata.AttributesData;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.entity.attribute.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = EntityData.EntityDataDeserializer.class)
@SuppressWarnings("unused")
public class EntityData {
    protected final static LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    @JsonProperty
    private final EntityType entityType;
    @JsonProperty
    private final boolean shouldDespawn;
    @JsonProperty
    @JsonAlias({"nametag"})
    private final String nameTag;
    @JsonProperty
    private final boolean customNameVisible;
    @JsonProperty
    private final boolean silent;
    @JsonProperty
    private final boolean visualFire;
    @JsonProperty("attributes")
    private final AttributesData attributesData;
    @JsonProperty("passenger")
    private EntityData passengerData;
    private final List<EntityAttributeData> entitySpecificAttributes = new ArrayList<>();

    protected EntityData(ConfigurationSection configurationSection) {
        ConfigurationSection rootSection = configurationSection.getName().equals("extra") ? configurationSection.getParent() : configurationSection;

        entityType = EntityType.valueOf(rootSection.getString("type", "ZOMBIE").toUpperCase());
        shouldDespawn = rootSection.getBoolean("shouldDespawn", true);

        nameTag = rootSection.contains("nametag") ? LEGACY_SERIALIZER.serialize(MiniMessage.miniMessage().deserialize(rootSection.getString("nametag"))) : null;
        customNameVisible = rootSection.getBoolean("customNameVisible", false);
        silent = rootSection.getBoolean("silent");
        visualFire = rootSection.getBoolean("visualFire");
        
        ConfigurationSection attributeSection = rootSection.getConfigurationSection("attributes");
        attributesData = attributeSection != null ? new AttributesData(attributeSection, configurationSection.getCurrentPath()) : new AttributesData();
        
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

        attributesData = new AttributesData();
    }

    public Entity spawnEntity(@NotNull Location location) {
        Entity entity = location.getWorld().spawnEntity(location, entityType);
        applyExtraMetadata(entity);
        entitySpecificAttributes.forEach(attributeData -> attributeData.apply(entity));
        return entity;
    }

    public void applyExtraMetadata(Entity entity) {
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

    public AttributesData getAttributeData() {
        return attributesData;
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
            try {
                return EntityData.create(EntityType.valueOf(parentSection.getString(path)));
            } catch(IllegalArgumentException e) {
                ConfigurationData.getLogger().severe("'" + parentSection.getString(path) + "' at " + parentSection.getCurrentPath() + "." + path + " is not a valid entity.");
                return null;
            }
        } else {
            ConfigurationSection entitySection = parentSection.getConfigurationSection(path);
            return entitySection != null ? create(entitySection, def) : EntityData.create(def);
        }
    }

    public static EntityData create(ConfigurationSection configurationSection, @NotNull EntityType def) {
        String entityTypeRaw = configurationSection.getString("type");
        EntityType entityType;
        try {
            entityType = entityTypeRaw != null ? EntityType.valueOf(entityTypeRaw.toUpperCase()) : def;
        } catch(IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("'" + entityTypeRaw + "' at " + configurationSection.getCurrentPath() + ".entity is not a valid entity.");
            return null;
        }

        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecated: The contents of the 'extra' section at '" + configurationSection.getCurrentPath() + "' should now be placed in the root of the entity section");
        }

        EntityDataRegistry.EntityDataConstructor entityDataConstructor = EntityDataRegistry.getEntityDataClass(entityType);
        return entityDataConstructor != null ? entityDataConstructor.apply(configurationSection) : null;
    }

    public static class EntityDataDeserializer extends JsonDeserializer<EntityData> {

        @Override
        public EntityData deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            JsonNode node = parser.getCodec().readTree(parser);

            String entityTypeRaw = node.get("type").asText();
            EntityType entityType;
            try {
                // TODO: Work out how to handle defaults
                entityType = entityTypeRaw != null ? EntityType.valueOf(entityTypeRaw.toUpperCase()) : def;
            } catch(IllegalArgumentException e) {
                JsonLocation location = parser.currentLocation();
                ConfigurationData.getLogger().severe("'" + entityTypeRaw + "' at %s:%s is not a valid entity.".formatted(location.getLineNr(), location.getColumnNr()));
                return null;
            }

            Class<?> entityDataClass = EntityDataRegistry.getEntityDataClass(entityType);
            JavaType javaType = context.getTypeFactory().constructType(entityDataClass);
            JsonDeserializer<?> deserializer = context.findRootValueDeserializer(javaType);
            return (EntityData) deserializer.deserialize(parser, context);
        }
    }
}
