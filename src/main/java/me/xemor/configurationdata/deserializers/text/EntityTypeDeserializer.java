package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.entity.EntityType;

public class EntityTypeDeserializer extends TextDeserializer<EntityType> {

    public EntityTypeDeserializer() {}

    @Override
    public EntityType deserialize(String text) {
        return Registry.ENTITY_TYPE.match(text);
    }

}
