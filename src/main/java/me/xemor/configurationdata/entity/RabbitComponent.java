package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private Rabbit.Type variant = null;

    @Override
    public void apply(Entity entity, EntityData entityData) {
        Rabbit rabbit = (Rabbit) entity;
        if (variant != null) rabbit.setRabbitType(variant);
    }
}
