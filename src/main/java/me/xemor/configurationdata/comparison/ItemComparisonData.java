package me.xemor.configurationdata.comparison;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class ItemComparisonData {

    private SetData<Material> types;
    private RangeData amount;

    public ItemComparisonData(ConfigurationSection configurationSection) {
        types = new SetData<>(Material.class, "types", configurationSection);
        amount = new RangeData("amount", configurationSection);
    }

}
