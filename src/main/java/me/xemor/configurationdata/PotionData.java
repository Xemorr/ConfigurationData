package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class PotionData {

    private final org.bukkit.potion.PotionData potionData;

    public PotionData(ConfigurationSection configurationSection) {
        potionData = new org.bukkit.potion.PotionData(PotionType.valueOf(configurationSection.getString("type")), configurationSection.getBoolean("extended"), configurationSection.getBoolean("upgraded"));
    }

    public void applyPotion(ItemMeta itemMeta) {
        PotionMeta potionMeta = (PotionMeta) itemMeta;
        potionMeta.setBasePotionData(potionData);
    }
}
