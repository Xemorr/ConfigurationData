package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PotionMetaData extends ItemMetaData {

    public PotionMetaData(ConfigurationSection configurationSection, ItemMeta baseMeta) {
        super(configurationSection, baseMeta);
        PotionMeta potionMeta = (PotionMeta) baseMeta;

        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potionSection != null) {
            String potionTypeStr = potionSection.getString("type");
            PotionType potionType = null;
            try {
                potionType = PotionType.valueOf(potionTypeStr);
            } catch(IllegalArgumentException e) {
                ConfigurationData.getLogger().severe("Invalid potion type entered at " + configurationSection.getCurrentPath() + ".type, " + potionTypeStr + " is invalid!");
            }
            boolean extended = potionSection.getBoolean("extended", false);
            boolean upgraded = potionSection.getBoolean("upgraded", false);
            PotionData potionData = new PotionData(potionType, extended, upgraded);
            potionMeta.setBasePotionData(potionData);
        }
    }
}
