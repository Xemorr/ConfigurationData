package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ItemStackData;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;

public class PotionEntityData extends ExtraData {

    ItemStack potion;

    public PotionEntityData(ConfigurationSection configurationSection) {
        super(configurationSection);
        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potion != null) {
            potion = new ItemStackData(potionSection).getItem();
        }
        else {
            potion = new ItemStack(Material.POTION);
        }
    }

    @Override
    public void applyData(Entity entity) {
        ThrownPotion thrownPotion = (ThrownPotion) entity;
        thrownPotion.setItem(potion);
    }
}
