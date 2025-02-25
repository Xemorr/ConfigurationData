package me.xemor.configurationdata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public record ItemStackData(Material type, int amount, ItemMetaData metadata) {
    /**
     * Please use ItemComparisonData instead.
     */
    @Deprecated
    public boolean isEqual(ItemStack item) {
        return item.getType() == item().getType() && item.hasItemMeta() == item().hasItemMeta() && (!item.hasItemMeta() || Bukkit.getItemFactory().equals(item.getItemMeta(), item().getItemMeta()));
    }

    /**
     * Please use ItemComparisonData instead
     *
     * An equals method that ignores whether there is multiple of the item, or if the item has been damaged.
     * @param other - the itemstack to compare against
     * @return a boolean describing whether the two items match
     */
    @Deprecated
    public boolean fuzzyEquals(ItemStack other) {
        ItemStack copy = other.clone();
        copy.setAmount(1);
        if (copy.hasItemMeta()) {
            ItemMeta metaCopy = copy.getItemMeta();
            if (metaCopy instanceof Damageable) {
                ((Damageable) metaCopy).setDamage(0);
            }
            copy.setItemMeta(metaCopy);
        }
        return copy.equals(item());
    }

    public ItemStack item() {
        ItemStack item = new ItemStack(type);
        item.setAmount(amount);
        item.setItemMeta(metadata.createItemMeta(item.getType()));
        return item;
    }

}
