package me.xemor.configurationdata;

import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public record TrimData(TrimPattern pattern, TrimMaterial material) {

    public void applyTrim(ItemMeta meta) {
        if (pattern == null || material == null) return;
        if (meta instanceof ArmorMeta armorMeta) {
            armorMeta.setTrim(new ArmorTrim(material, pattern));
        }
    }

}
