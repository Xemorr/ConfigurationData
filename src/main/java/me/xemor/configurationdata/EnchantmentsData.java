package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import me.xemor.configurationdata.deserializers.EnchantmentDeserializer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentsData {

    private final HashMap<Enchantment, Integer> enchantments = new HashMap<>();

    @JsonAnySetter
    public void addEnchantment(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
    }

    public HashMap<Enchantment, Integer> getEnchantments() {
        return enchantments;
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
