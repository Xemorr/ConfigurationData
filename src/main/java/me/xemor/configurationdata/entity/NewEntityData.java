package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.xemor.configurationdata.AttributesData;
import me.xemor.configurationdata.deserializers.NewEntityDataDeserializer;
import me.xemor.configurationdata.entity.components.EntityComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = NewEntityDataDeserializer.class)
public final class NewEntityData {

    protected final static LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    private EntityType type = EntityType.ZOMBIE;
    private boolean shouldDespawn = true;
    private String nameTag;
    private boolean customNameVisible = false;
    private boolean silent = false;
    private boolean visualFire = false;
    private AttributesData attributes = null;
    private NewEntityData passenger = null;
    private List<EntityComponent> entitySpecificAttributes = new ArrayList<>();

    public NewEntityData setNameTag(String nameTag) {
        this.nameTag = nameTag;
        return this;
    }

    public NewEntityData setType(EntityType type) {
        this.type = type;
        return this;
    }

    public NewEntityData shouldDespawn(boolean shouldDespawn) {
        this.shouldDespawn = shouldDespawn;
        return this;
    }

    public NewEntityData setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
        return this;
    }

    public NewEntityData setSilent(boolean silent) {
        this.silent = silent;
        return this;
    }

    public NewEntityData setVisualFire(boolean visualFire) {
        this.visualFire = visualFire;
        return this;
    }

    public NewEntityData setAttributes(AttributesData attributes) {
        this.attributes = attributes;
        return this;
    }

    public NewEntityData setPassenger(NewEntityData passenger) {
        this.passenger = passenger;
        return this;
    }

    public NewEntityData setEntitySpecificAttributes(List<EntityComponent> entitySpecificAttributes) {
        this.entitySpecificAttributes = entitySpecificAttributes;
        return this;
    }

    public NewEntityData getPassenger() {
        return passenger;
    }

    public AttributesData getAttributes() {
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
