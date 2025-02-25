package me.xemor.configurationdata.comparison;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.configuration.ConfigurationSection;

import java.util.logging.Level;

public record BlockDataComparisonData(SetData<Material> types, RangeData level, RangeData age) {
    public boolean matches(BlockData blockData) {
        if (blockData == null) return false;
        boolean value = types.inSet(blockData.getMaterial());

        if (blockData instanceof Levelled levelled) value &= level.isInRange(levelled.getLevel());
        if (blockData instanceof Ageable ageable) value &= age.isInRange(ageable.getAge());

        return value;
    }
}
