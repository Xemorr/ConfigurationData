package me.xemor.configurationdata;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMetaData {

    private final ItemMeta itemMeta;
    private final static LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();


    public ItemMetaData(ConfigurationSection configurationSection, ItemMeta baseMeta) {
        itemMeta = baseMeta.clone();
        String displayName = configurationSection.getString("displayName");
        if (displayName != null) {
            Component component = MiniMessage.miniMessage().deserialize(displayName);
            itemMeta.setDisplayName(legacySerializer.serialize(component));
        }
        List<String> lore = configurationSection.getStringList("lore");
        lore = lore.stream().map(string -> legacySerializer.serialize(MiniMessage.miniMessage().deserialize(string))).collect(Collectors.toList());
        itemMeta.setLore(lore);
        boolean isUnbreakable = configurationSection.getBoolean("isUnbreakable", false);
        itemMeta.setUnbreakable(isUnbreakable);
        int durability = configurationSection.getInt("durability", 0);
        if (durability != 0 && itemMeta instanceof Damageable) {
            Damageable damageable = (Damageable) itemMeta;
            damageable.setDamage(durability);
        }
        ConfigurationSection attributeSection = configurationSection.getConfigurationSection("attributes");
        if (attributeSection != null) {
            ItemAttributeData attributeData = new ItemAttributeData(attributeSection);
            attributeData.applyAttributes(itemMeta);
        }
        ConfigurationSection enchantSection = configurationSection.getConfigurationSection("enchants");
        if (enchantSection != null) {
            EnchantmentData enchantmentData = new EnchantmentData(enchantSection);
            enchantmentData.applyEnchantments(itemMeta);
        }
        if (itemMeta instanceof LeatherArmorMeta) {
            LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemMeta;
            int red = configurationSection.getInt("color.red", -1);
            int green = configurationSection.getInt("color.green", -1);
            int blue = configurationSection.getInt("color.blue", -1);
            Color color;
            if (red == -1 || blue == -1 || green == -1) {
                color = null;
            } else {
                color = Color.fromRGB(red, green, blue);
            }
            armorMeta.setColor(color);
        }
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

}
