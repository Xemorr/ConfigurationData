package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.deserializers.NewEntityDataDeserializer;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;

public class RabbitComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private Rabbit.Type variant = null;

    @Override
    public void apply(Entity entity, NewEntityData newEntityData) {
        Rabbit rabbit = (Rabbit) entity;
        if (variant != null) rabbit.setRabbitType(variant);
    }
}
