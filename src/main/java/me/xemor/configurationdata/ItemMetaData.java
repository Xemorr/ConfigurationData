package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemMetaData {

    private static LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    @JsonPropertyWithDefault
    private String displayName = null;
    @JsonPropertyWithDefault
    private List<String> lore = null;
    @JsonPropertyWithDefault
    private boolean isUnbreakable = false;
    @JsonPropertyWithDefault
    private int durability = 0;
    @JsonPropertyWithDefault
    private int customModelData = 0;
    @JsonPropertyWithDefault
    private AttributesData attributes = null;
    @JsonPropertyWithDefault
    private TrimData trim = null;
    @JsonPropertyWithDefault
    private List<ItemFlag> flags = new ArrayList<>();
    @JsonPropertyWithDefault
    private BookData book = null;
    @JsonPropertyWithDefault
    private LeatherArmorColor color = null;
    @JsonPropertyWithDefault
    private Map<Enchantment, Integer> enchantments = new HashMap<>();

    public ItemMeta createItemMeta(Material material) {
        return applyToItemMeta(Bukkit.getItemFactory().getItemMeta(material));
    }

    public ItemMeta applyToItemMeta(ItemMeta baseMeta) {
        ItemMeta meta = baseMeta.clone();
        if (displayName != null) {
            Component component = MiniMessage.miniMessage().deserialize(displayName);
            meta.setDisplayName(legacySerializer.serialize(component));
        }
        if (lore != null) {
            lore = lore.stream().map(string -> legacySerializer.serialize(MiniMessage.miniMessage().deserialize(string))).collect(Collectors.toList());
            meta.setLore(lore);
        }

        meta.setUnbreakable(isUnbreakable);
        meta.setCustomModelData(customModelData);

        if (attributes != null) attributes.applyAttributes(meta);

        if (!enchantments.isEmpty()) applyEnchantments(meta);

        if (trim != null) trim.applyTrim(meta);

        for (ItemFlag flag : flags) {
            meta.addItemFlags(flag);
        }

        if (durability != 0 && meta instanceof Damageable damageable) damageable.setDamage(durability);

        if (meta instanceof LeatherArmorMeta leatherMeta && color != null) color.handleLeatherArmor(leatherMeta);

        if (meta instanceof BookMeta bookMeta && book != null) book.applyToBookMeta(bookMeta);

        return meta;
    }

    public void applyEnchantments(ItemMeta itemMeta) {
        if (itemMeta instanceof EnchantmentStorageMeta enchantmentStorageMeta) {
            for (Map.Entry<Enchantment, Integer> item : enchantments.entrySet()) {
                enchantmentStorageMeta.addStoredEnchant(item.getKey(), item.getValue(), true);
            }
        }
        else {
            for (Map.Entry<Enchantment, Integer> item : enchantments.entrySet()) {
                itemMeta.addEnchant(item.getKey(), item.getValue(), true);
            }
        }
    }

}
