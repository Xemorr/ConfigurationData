package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AxolotlComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private Axolotl.Variant variant = null;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Axolotl axolotl = (Axolotl) entity;
        if (variant != null) {
            axolotl.setVariant(variant);
        }
    }
}
