package me.xemor.configurationdata.comparison;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.configuration.ConfigurationSection;

import java.util.logging.Level;

public class BlockDataComparisonData {
    private final SetData<Material> types;
    private final RangeData level;
    private final RangeData age;

    public BlockDataComparisonData(ConfigurationSection configurationSection) {
        types = new SetData<>(Material.class, "types", configurationSection);
        if (types.getSet().isEmpty()) {
            types.getSet().add(Material.valueOf(configurationSection.getString("type", "STONE")));
        }
        level = new RangeData("level", configurationSection);
        age = new RangeData("age", configurationSection);
    }

    public boolean matches(BlockData blockData) {
        if (blockData==null) return false;
        boolean value = types.inSet(blockData.getMaterial());

        if (blockData instanceof Levelled levelled) value &= level.isInRange(levelled.getLevel());
        if (blockData instanceof Ageable ageable) value &= age.isInRange(ageable.getAge());

        return value;
    }

}
