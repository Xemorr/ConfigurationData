package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ItemMetaComparisonData {

    private final String displayName;
    private final LoreData lore;

    public ItemMetaComparisonData(ConfigurationSection configurationSection) {
        displayName = configurationSection.getString("displayName");
        lore = new LoreData("lore", configurationSection);
    }

}
