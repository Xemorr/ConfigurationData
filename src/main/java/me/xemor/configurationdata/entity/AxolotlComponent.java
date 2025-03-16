package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;

public class AxolotlComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private Axolotl.Variant variant = null;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Axolotl axolotl = (Axolotl) entity;
        if (variant != null) {
            axolotl.setVariant(variant);
        }
    }
}
