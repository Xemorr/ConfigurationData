package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class PotionMetaData extends ItemMetaData {

    private PotionMeta potionMeta;

    public PotionMetaData(ConfigurationSection configurationSection, ItemMeta baseMeta) {
        super(configurationSection, baseMeta);
        potionMeta = (PotionMeta) baseMeta;

        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potionSection != null) {
            PotionData potionData = new PotionData(potionSection);
            potionData.applyPotion(potionMeta);
        }
    }

    public PotionMeta getItemMeta()
    {
        return potionMeta;
    }
}
