package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;

public class EnchantComparisonData {

    private final Map<Enchantment, RangeData> enchantMap = new HashMap<>();

    @JsonAnySetter
    public void addEnchantment(Enchantment enchantment, RangeData rangeData) {
        enchantMap.put(enchantment, rangeData);
    }

    public boolean matches(Map<Enchantment, Integer> enchantments) {
        if (enchantments.isEmpty() && !enchantMap.isEmpty()) return false;
        for (Map.Entry<Enchantment, RangeData> entry : enchantMap.entrySet()) {
            Enchantment enchantment = entry.getKey();
            RangeData levelRange = entry.getValue();
            Integer level = enchantments.get(enchantment);
            if (level == null) return false;
            if (!levelRange.isInRange(level)) return false;
        }
        return true;
    }
}
