package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LlamaComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private Llama.Color color = null;
    @JsonPropertyWithDefault
    private int strength = 1;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Llama llama = (Llama) entity;
        if (color != null) {
            llama.setColor(color);
        }
        llama.setStrength(strength);
    }
}
