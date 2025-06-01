package me.xemor.configurationdata.comparison;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantComparisonData {

    @JsonPropertyWithDefault
    private final Map<Enchantment, RangeData> enchantMap = new HashMap<>();

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
