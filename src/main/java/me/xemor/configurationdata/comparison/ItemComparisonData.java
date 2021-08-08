package me.xemor.configurationdata.comparison;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemComparisonData {

    private final SetData<Material> types;
    private final RangeData amount;
    private final ItemMetaComparisonData itemMetaData;

    public ItemComparisonData(ConfigurationSection configurationSection) {
        types = new SetData<>(Material.class, "types", configurationSection);
        amount = new RangeData("amount", configurationSection);
        ConfigurationSection metadataSection = configurationSection.getConfigurationSection("metadata");
        itemMetaData = new ItemMetaComparisonData(metadataSection);
    }

    public boolean matches(ItemStack item) {
        ItemMeta meta;
        if (item.hasItemMeta()) meta = item.getItemMeta();
        else meta = Bukkit.getItemFactory().getItemMeta(item.getType());
        return types.inSet(item.getType()) && amount.isInRange(item.getAmount()) && itemMetaData.matches(meta);
    }

}
