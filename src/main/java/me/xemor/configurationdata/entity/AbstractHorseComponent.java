package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractHorseComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private int tamingDifficulty = 1;
    @JsonPropertyWithDefault
    private double jumpStrength = 0.7;
    @JsonPropertyWithDefault
    private boolean tamed = false;
    @JsonPropertyWithDefault
    private boolean hasSaddle = false;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        AbstractHorse abstractHorse = (AbstractHorse) entity;
        abstractHorse.getInventory().setSaddle(hasSaddle ? new ItemStack(Material.SADDLE) : null);
        abstractHorse.setMaxDomestication(tamingDifficulty);
        abstractHorse.setJumpStrength(jumpStrength);
        abstractHorse.setTamed(tamed);
    }
}
