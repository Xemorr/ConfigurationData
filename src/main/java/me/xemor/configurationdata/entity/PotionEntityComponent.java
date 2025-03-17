package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PotionEntityComponent implements EntityComponent {
    @JsonPropertyWithDefault
    private ItemStack potion = new ItemStack(Material.SPLASH_POTION);

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        ThrownPotion thrownPotion = (ThrownPotion) entity;
        thrownPotion.setItem(potion);
    }
}
