package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Pattern;

public class ItemMetaComparisonData {

    private final Pattern displayName;
    private final LoreData lore;
    private EnchantComparisonData enchantComparisonData = null;

    public ItemMetaComparisonData(ConfigurationSection configurationSection) {
        displayName = Pattern.compile(configurationSection.getString("displayName", ""));
        lore = new LoreData("lore", configurationSection);
        ConfigurationSection enchantSection = configurationSection.getConfigurationSection("enchants");
        if (enchantSection != null) {
            enchantComparisonData = new EnchantComparisonData(enchantSection);
        }
    }

    public boolean matches(ItemMeta meta) {
        boolean matching = matchDisplayName(meta.getDisplayName()) && lore.matches(meta.getLore());
        if (enchantComparisonData != null) {
            matching |= enchantComparisonData.matches(meta.getEnchants());
        }
        return matching;
    }

    public boolean matchDisplayName(String name) {
        if (name == null) name = "";
        return displayName.matcher(name).matches();
    }

}
