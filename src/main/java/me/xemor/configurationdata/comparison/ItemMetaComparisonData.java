package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public record ItemMetaComparisonData(Pattern displayName, LoreData lore, EnchantComparisonData enchantComparisonData) {

    private final static LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    @JsonCreator
    public ItemMetaComparisonData(@JsonProperty("displayName") String displayName,
                                  @JsonProperty("lore") LoreData lore,
                                  @JsonProperty("enchants") EnchantComparisonData enchantComparisonData
    ) {
        this(
                Pattern.compile(legacySerializer.serialize(MiniMessage.miniMessage().deserialize(displayName == null ? ".+" : displayName))),
                lore == null ? new LoreData(List.of()) : lore,
                enchantComparisonData
        );
    }

    public boolean matches(ItemMeta meta) {
        if (meta == null) return false;
        boolean matching = matchDisplayName(meta.getDisplayName()) && lore.matches(meta.getLore());
        if (enchantComparisonData != null) {
            Map<Enchantment, Integer> enchantMap;
            if (meta instanceof EnchantmentStorageMeta) {
                EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) meta;
                enchantMap = enchantmentStorageMeta.getStoredEnchants();
            }
            else {
                enchantMap = meta.getEnchants();
            }
            matching &= enchantComparisonData.matches(enchantMap);
        }
        if (meta instanceof PotionMeta) {
            PotionMeta potionMeta = (PotionMeta) meta;
            potionMeta.getBasePotionData().getType();
        }
        return matching;
    }

    public boolean matchDisplayName(String name) {
        if (name == null) name = "";
        return displayName.matcher(name).matches();
    }

}
