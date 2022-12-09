package me.xemor.configurationdata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.configuration.ConfigurationSection;

public class BlockDataData {

    private final BlockData blockData;

    public BlockDataData(ConfigurationSection configurationSection) {
        Material material = Material.valueOf("STONE");
        try {
            material = Material.valueOf(configurationSection.getString("type", "STONE"));
        } catch (IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("Invalid Type for Block at " + configurationSection.getCurrentPath() + ".type");
            e.printStackTrace();
        }
        blockData = Bukkit.createBlockData(material);

        int level = configurationSection.getInt("level", -1);
        if (level != -1 && blockData instanceof Levelled levelled) levelled.setLevel(level);

        int age = configurationSection.getInt("age",-1);
        if (age != -1 && blockData instanceof Ageable ageable) ageable.setAge(age);
    }

    public BlockData getBlockData() {
        return blockData.clone();
    }
}
