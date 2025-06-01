package me.xemor.configurationdata.comparison;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class ItemComparisonData {

    @JsonPropertyWithDefault
    private SetData<Material> types = new SetData<>();
    @JsonPropertyWithDefault
    private Material type;
    @JsonPropertyWithDefault
    private RangeData amount = new RangeData();
    @JsonPropertyWithDefault
    private ItemMetaComparisonData metadata = null;

    public ItemComparisonData() {}

    public ItemComparisonData(Set<Material> set) { this.types = new SetData<>(set); }

    public boolean matches(ItemStack item) {
        if (types.getSet().isEmpty() && type != null) types = new SetData<>(Set.of(type));
        ItemMeta meta;
        if (item == null && types.inSet(Material.AIR)) return true;
        if (item == null) return false;
        if (item.hasItemMeta() && item.getItemMeta() != null) meta = item.getItemMeta();
        else meta = Bukkit.getItemFactory().getItemMeta(item.getType());
        boolean value = types.inSet(item.getType()) && amount.isInRange(item.getAmount());
        if (metadata != null) {
            value &= metadata.matches(meta);
        }
        return value;
    }
}
