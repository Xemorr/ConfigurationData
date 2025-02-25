package me.xemor.configurationdata.comparison;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public record ItemComparisonData(SetData<Material> types, RangeData amount, @Nullable ItemMetaComparisonData itemMetaData) {
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
