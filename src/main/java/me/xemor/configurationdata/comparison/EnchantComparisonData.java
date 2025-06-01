package me.xemor.configurationdata.comparison;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import me.xemor.configurationdata.JsonAnySetterWithTypedKey;
import me.xemor.configurationdata.deserializers.text.RegistryDeserializer;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantComparisonData {

    @JsonPropertyWithDefault
    private final Map<Enchantment, RangeData> enchantMap = new HashMap<>();

    @JsonAnySetterWithTypedKey(Enchantment.class)
    public void addEnchantment(String enchantmentStr, RangeData rangeData) {
        Enchantment enchantment = ENCHANTMENT_DESERIALIZER.parse(enchantmentStr);
        if (enchantment == null) return;
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
