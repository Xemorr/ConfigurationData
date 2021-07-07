package me.xemor.configurationdata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class ItemStackData {

    private ItemStack item;

    public ItemStackData(ConfigurationSection configurationSection, String defaultMaterial) {
        init(configurationSection, defaultMaterial);
    }

    public ItemStackData(ConfigurationSection configurationSection) {
        init(configurationSection, "STONE");
    }

    public void init(ConfigurationSection configurationSection, String defaultMaterial) {
        Material material = Material.valueOf(configurationSection.getString("type", defaultMaterial).toUpperCase());
        int amount = configurationSection.getInt("amount", 1);
        item = new ItemStack(material, amount);
        ConfigurationSection metadataSection = configurationSection.getConfigurationSection("metadata");
        if (metadataSection != null) {
            ItemMeta meta = Bukkit.getItemFactory().getItemMeta(material);
            if (meta != null) {
                ItemMetaData itemMetaData;
                if (item.getType() == Material.POTION
                        || item.getType() == Material.SPLASH_POTION
                        || item.getType() == Material.LINGERING_POTION) itemMetaData = new PotionMetaData(metadataSection, meta);
                else itemMetaData = new ItemMetaData(metadataSection, meta);
                item.setItemMeta(itemMetaData.getItemMeta());
            }
        }
    }


    /**
     * Please use fuzzyEquals instead.
     */
    @Deprecated
    public boolean isEqual(ItemStack item) {
        return item.getType() == this.item.getType() && item.hasItemMeta() == this.item.hasItemMeta() && (!item.hasItemMeta() || Bukkit.getItemFactory().equals(item.getItemMeta(), this.item.getItemMeta()));
    }

    /**
     * An equals method that ignores whether there is multiple of the item, or if the item has been damaged.
     * @param other - the itemstack to compare against
     * @return a boolean describing whether the two items match
     */
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
        return copy.equals(item);
    }

    public ItemStack getItem()
    {
        return item;
    }

}
