package me.xemor.configurationdata;

import me.xemor.configurationdata.comparison.ItemMetaComparisonData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.BookMeta;
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
        // Handles custom models from resource packs
        int customModelData = configurationSection.getInt("customModelData", 0);
        itemMeta.setCustomModelData(customModelData);
        //
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
        handleLeatherArmor(configurationSection, itemMeta);
        handleBooks(configurationSection, itemMeta);
    }

    public void handleLeatherArmor(ConfigurationSection section, ItemMeta meta) {
        if (meta instanceof LeatherArmorMeta armorMeta) {
            int red = section.getInt("color.red", -1);
            int green = section.getInt("color.green", -1);
            int blue = section.getInt("color.blue", -1);
            Color color;
            if (red == -1 || blue == -1 || green == -1) {
                color = null;
            } else {
                color = Color.fromRGB(red, green, blue);
            }
            armorMeta.setColor(color);
        }
    }

    public void handleBooks(ConfigurationSection section, ItemMeta meta) {
        if (meta instanceof BookMeta bookMeta) {
            List<String> pages = section.getStringList("book.pages").stream()
                    .map((s) -> legacySerializer.serialize(MiniMessage.miniMessage().deserialize(s)))
                    .toList();
            String author = section.getString("book.author", "");
            String title = legacySerializer.serialize(MiniMessage.miniMessage().deserialize(section.getString("book.title", "Xemor is cool")));
            bookMeta.setGeneration(BookMeta.Generation.ORIGINAL);
            bookMeta.setAuthor(author);
            bookMeta.setPages(pages);
            bookMeta.setTitle(title);
        }
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

}
