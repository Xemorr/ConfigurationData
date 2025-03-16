package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;

public class PotionEntityComponent implements EntityComponent {
    @JsonSetter(nulls = Nulls.SKIP)
    private final ItemStack potion = new ItemStack(Material.SPLASH_POTION);

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        ThrownPotion thrownPotion = (ThrownPotion) entity;
        thrownPotion.setItem(potion);
    }
}
