package me.xemor.configurationdata.comparison;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.configuration.ConfigurationSection;
import org.w3c.dom.ranges.Range;

import java.util.logging.Level;

public class BlockDataComparisonData {

    @JsonPropertyWithDefault
    private SetData<Material> types = new SetData<>();
    @JsonPropertyWithDefault
    private RangeData level = new RangeData();
    @JsonPropertyWithDefault
    private RangeData age = new RangeData();

    public boolean matches(BlockData blockData) {
        if (blockData == null) return false;
        boolean value = types.inSet(blockData.getMaterial());

        if (blockData instanceof Levelled levelled) value &= level.isInRange(levelled.getLevel());
        if (blockData instanceof Ageable ageable) value &= age.isInRange(ageable.getAge());

        return value;
    }
}
