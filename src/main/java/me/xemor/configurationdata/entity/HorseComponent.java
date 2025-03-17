package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HorseComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private int tamingDifficulty = 1;
    @JsonPropertyWithDefault
    private double jumpStrength = 0.7;
    @JsonPropertyWithDefault
    private boolean hasChest = false;
    @JsonPropertyWithDefault
    private boolean tamed = false;
    @JsonPropertyWithDefault
    private ItemStack armor = null;
    @JsonPropertyWithDefault
    private boolean hasSaddle = false;
    @JsonPropertyWithDefault
    private Horse.Color color = Horse.Color.CHESTNUT;
    @JsonPropertyWithDefault
    private Horse.Style style = Horse.Style.NONE;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        if (entity instanceof AbstractHorse horse) {
            horse.getInventory().setSaddle(hasSaddle ? new ItemStack(Material.SADDLE) : null);
            horse.setMaxDomestication(tamingDifficulty);
            horse.setJumpStrength(jumpStrength);
            horse.setTamed(tamed);
        }

        if (entity instanceof Horse horse) {
            horse.getInventory().setArmor(armor);
            horse.setColor(color);
            horse.setStyle(style);
        }
        if (entity instanceof ChestedHorse chestedHorse) {
            chestedHorse.setCarryingChest(hasChest);
        }
    }
}
