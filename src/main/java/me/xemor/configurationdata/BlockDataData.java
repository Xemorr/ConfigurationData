package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

public class BlockDataData {

    @JsonPropertyWithDefault
    @JsonAlias("type")
    private Material material = Material.STONE;
    @JsonPropertyWithDefault
    private int level = -1;
    @JsonPropertyWithDefault
    private int age = -1;

    public BlockData getBlockData() {
        BlockData blockData = Bukkit.createBlockData(material);
        if (level != -1 && blockData instanceof Levelled levelled) levelled.setLevel(level);
        if (age != -1 && blockData instanceof Ageable ageable) ageable.setAge(age);
        return blockData;
    }
}
