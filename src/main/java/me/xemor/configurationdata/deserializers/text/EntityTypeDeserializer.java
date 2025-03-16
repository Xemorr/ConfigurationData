package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.entity.EntityType;

public class EntityTypeDeserializer extends TextDeserializer<EntityType> {

    private EntityType defaultEntityType = EntityType.ZOMBIE;

    public EntityTypeDeserializer() {}
    public EntityTypeDeserializer(EntityType defaultEntityType) { this.defaultEntityType = defaultEntityType; }

    @Override
    public TextDeserializer.Deserialized<EntityType> deserialize(String text) {
        EntityType entityType = Registry.ENTITY_TYPE.match(text);
        if (entityType == null) return new TextDeserializer.Deserialized<>(defaultEntityType, true);
        return new TextDeserializer.Deserialized<>(entityType, false);
    }

}
