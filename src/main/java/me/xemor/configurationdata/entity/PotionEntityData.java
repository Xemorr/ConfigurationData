package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ItemStackData;
import me.xemor.configurationdata.PotionEffectData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SplashPotion;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

public class PotionEntityData extends ExtraData {

    ItemStack potion;

    public PotionEntityData(ConfigurationSection configurationSection) {
        super(configurationSection);
        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        potion = new ItemStackData(potionSection).getItem();
    }

    @Override
    public void applyData(Entity entity) {
        ThrownPotion thrownPotion = (ThrownPotion) entity;
        thrownPotion.setItem(potion);
    }
}
