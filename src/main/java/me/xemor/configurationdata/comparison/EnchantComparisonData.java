package me.xemor.configurationdata.comparison;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;

public class EnchantComparisonData {

    private boolean noEnchants;
    private final Map<Enchantment, RangeData> enchantMap = new HashMap<>();

    public EnchantComparisonData(ConfigurationSection enchantSection) {
        for (Map.Entry<String, Object> entry : enchantSection.getValues(false).entrySet()) {
            String name = entry.getKey();
            if ("NONE".equals(entry.getKey())) noEnchants = false;
            Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(name.toLowerCase()));
            if (entry.getValue() instanceof String) {
                enchantMap.put(enchant, new RangeData((String) entry.getValue()));
            }
        }
    }

    public boolean matches(Map<Enchantment, Integer> enchantments) {
        if (enchantments.size() == 0 && enchantMap.size() > 0) return false;
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            RangeData rangeData = enchantMap.get(entry.getKey());
            if (rangeData == null) return false;
            if (!rangeData.isInRange(entry.getValue())) return false;
        }
        return true;
    }



}
