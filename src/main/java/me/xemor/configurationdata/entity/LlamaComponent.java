package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;

public class LlamaComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final Llama.Color color = null;
    @JsonPropertyWithDefault
    private final int strength = 1;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Llama llama = (Llama) entity;
        if (color != null) {
            llama.setColor(color);
        }
        llama.setStrength(strength);
    }
}
