package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class PotionMetaData extends ItemMetaData {

    public PotionMetaData(ConfigurationSection configurationSection, ItemMeta baseMeta) {
        super(configurationSection, baseMeta);
        PotionMeta potionMeta = (PotionMeta) baseMeta;

        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potionSection != null) {
            PotionData potionData = new PotionData(PotionType.valueOf(potionSection.getString("type")), potionSection.getBoolean("extended"), potionSection.getBoolean("upgraded"));
            potionMeta.setBasePotionData(potionData);
        }
    }
}
