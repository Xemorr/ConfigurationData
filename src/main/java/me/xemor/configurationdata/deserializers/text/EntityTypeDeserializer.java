package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.entity.EntityType;

public class EntityTypeDeserializer extends RegistryDeserializer<EntityType> {

    public EntityTypeDeserializer() {
        super(Registry.ENTITY_TYPE, "ZOMBIE");
    }
}
