package me.xemor.configurationdata;

import org.bukkit.Registry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public class TrimData {

    private TrimPattern pattern = null;
    private TrimMaterial material = null;

    public TrimData(ConfigurationSection trimSection) {
        String patternStr = trimSection.getString("pattern");
        if (patternStr != null) {
            pattern = Registry.TRIM_PATTERN.match(patternStr);
        }
        if (pattern == null) {
            ConfigurationData.getLogger().severe("You have entered an invalid trim pattern at: " + trimSection.getCurrentPath() + ".pattern");
        }
        String materialStr = trimSection.getString("material");
        if (materialStr != null) {
            material = Registry.TRIM_MATERIAL.match(materialStr);
        }
        if (material == null) {
            ConfigurationData.getLogger().severe("You have entered an invalid trim material at: " + trimSection.getCurrentPath() + ".material");
        }
    }

    public void applyTrim(ItemMeta meta) {
        if (pattern == null || material == null) return;
        if (meta instanceof ArmorMeta armorMeta) {
            armorMeta.setTrim(new ArmorTrim(material, pattern));
        }
    }

}
