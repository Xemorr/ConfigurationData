package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HorseComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private ItemStack armor = null;
    @JsonPropertyWithDefault
    private Horse.Color color = Horse.Color.CHESTNUT;
    @JsonPropertyWithDefault
    private Horse.Style style = Horse.Style.NONE;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        Horse horse = (Horse) entity;
        horse.getInventory().setArmor(armor);
        horse.setColor(color);
        horse.setStyle(style);
    }
}
