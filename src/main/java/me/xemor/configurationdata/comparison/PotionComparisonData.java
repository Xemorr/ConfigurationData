package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffectType;

public class PotionComparisonData {


    public PotionComparisonData(ConfigurationSection potionSections) {
        for (Object object : potionSections.getValues(false).values()) {
            if (object instanceof ConfigurationSection) {
                ConfigurationSection potionSection = (ConfigurationSection) object;
                String potionTypeStr = potionSection.getName();

            }
        }
    }

    public static class ComparablePotion {

        private PotionEffectType type;
        private RangeData potency;
        private RangeData duration;

        public ComparablePotion() {

        }
    }

}
