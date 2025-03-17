package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowComponent extends AbstractArrowComponent {

    @JsonPropertyWithDefault
    private PotionType potionType;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        org.bukkit.entity.Arrow arrow = (org.bukkit.entity.Arrow) entity;
        arrow.setBasePotionType(potionType);
    }
}
