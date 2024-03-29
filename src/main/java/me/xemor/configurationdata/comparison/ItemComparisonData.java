package me.xemor.configurationdata.comparison;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemComparisonData {

    private final SetData<Material> types;
    private final RangeData amount;
    private ItemMetaComparisonData itemMetaData = null;

    public ItemComparisonData(ConfigurationSection configurationSection) {
        types = new SetData<>(Material.class, "types", configurationSection);
        if (!configurationSection.contains("types", true)) {
            types.getSet().add(Material.valueOf(configurationSection.getString("type", "STONE")));
        }
        amount = new RangeData("amount", configurationSection);
        ConfigurationSection metadataSection = configurationSection.getConfigurationSection("metadata");
        if (metadataSection != null) {
            itemMetaData = new ItemMetaComparisonData(metadataSection);
        }
    }

    public boolean matches(ItemStack item) {
        ItemMeta meta;
        if (item == null) return false;
        if (item.hasItemMeta() && item.getItemMeta() != null) meta = item.getItemMeta();
        else meta = Bukkit.getItemFactory().getItemMeta(item.getType());
        boolean value = types.inSet(item.getType()) && amount.isInRange(item.getAmount());
        if (itemMetaData != null) {
            value &= itemMetaData.matches(meta);
        }
        return value;
    }

}
