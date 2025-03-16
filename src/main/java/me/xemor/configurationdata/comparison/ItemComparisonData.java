package me.xemor.configurationdata.comparison;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ItemComparisonData {

    @JsonPropertyWithDefault
    private SetData<Material> types = new SetData<>();
    @JsonPropertyWithDefault
    private RangeData amount = new RangeData();
    @JsonPropertyWithDefault
    private ItemMetaComparisonData itemMetaData = null;

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
