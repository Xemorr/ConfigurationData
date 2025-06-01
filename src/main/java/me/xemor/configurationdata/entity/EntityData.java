package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.xemor.configurationdata.AttributesData;
import me.xemor.configurationdata.deserializers.EntityDataDeserializer;
import me.xemor.configurationdata.entity.components.EntityComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonDeserialize(using = EntityDataDeserializer.class)
public final class EntityData {

    protected final static LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    private EntityType type = EntityType.ZOMBIE;
    private boolean shouldDespawn = true;
    private String nameTag;
    private boolean customNameVisible = false;
    private boolean silent = false;
    private boolean visualFire = false;
    private Map<Attribute, Double> attributes = null;
    private EntityData passenger = null;
    private List<EntityComponent> entitySpecificAttributes = new ArrayList<>();

    public EntityData setNameTag(String nameTag) {
        this.nameTag = nameTag;
        return this;
    }

    public EntityData setType(EntityType type) {
        this.type = type;
        return this;
    }

    public EntityData shouldDespawn(boolean shouldDespawn) {
        this.shouldDespawn = shouldDespawn;
        return this;
    }

    public EntityData setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
        return this;
    }

    public EntityData setSilent(boolean silent) {
        this.silent = silent;
        return this;
    }

    public EntityData setVisualFire(boolean visualFire) {
        this.visualFire = visualFire;
        return this;
    }

    public EntityData setAttributes(Map<Attribute, Double> attributes) {
        this.attributes = attributes;
        return this;
    }

    public EntityData setPassenger(EntityData passenger) {
        this.passenger = passenger;
        return this;
    }

    public EntityData setEntitySpecificAttributes(List<EntityComponent> entitySpecificAttributes) {
        this.entitySpecificAttributes = entitySpecificAttributes;
        return this;
    }

    public EntityData getPassenger() {
        return passenger;
    }

    public Map<Attribute, Double> getAttributes() {
        return attributes;
    }

    public boolean isVisualFire() {
        return visualFire;
    }

    public boolean isSilent() {
        return silent;
    }

    public boolean isCustomNameVisible() {
        return customNameVisible;
    }

    public String getNameTag() {
        return nameTag;
    }

    public boolean shouldDespawn() {
        return shouldDespawn;
    }

    public EntityType getType() {
        return type;
    }

    public Entity spawnEntity(@NotNull Location location) {
        Entity entity = location.getWorld().spawnEntity(location, type);
        applyExtraMetadata(entity);
        entitySpecificAttributes.forEach(attributeData -> attributeData.apply(entity,this));
        return entity;
    }

    public void applyExtraMetadata(Entity entity) {
        if (nameTag != null) entity.setCustomName(nameTag);

        entity.setCustomNameVisible(customNameVisible);
        entity.setPersistent(!shouldDespawn);
        entity.setSilent(silent);
        entity.setVisualFire(visualFire);

        if (passenger != null) {
            Entity spawnedPassenger = passenger.spawnEntity(entity.getLocation());
            entity.addPassenger(spawnedPassenger);
        }
    }


}
