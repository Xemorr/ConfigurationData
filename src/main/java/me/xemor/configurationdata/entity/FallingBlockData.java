package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.BlockDataData;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.jetbrains.annotations.NotNull;

public class FallingBlockData extends EntityData {

    private final BlockData blockData;
    private final boolean dropItem;
    private final boolean cancelDrop;
    private final boolean hurtEntities;
    private final float damagePerBlock;
    private final int maxDamage;

    public FallingBlockData(ConfigurationSection configurationSection) {
        super(configurationSection);

        blockData = new BlockDataData(configurationSection).getBlockData();
        dropItem = configurationSection.getBoolean("dropItem", true);
        cancelDrop = configurationSection.getBoolean("cancelDrop");
        hurtEntities = configurationSection.getBoolean("hurtEntities");
        damagePerBlock = (float) configurationSection.getDouble("damagePerBlock", 2);
        maxDamage = configurationSection.getInt("maxDamage", 40);
    }

    @Override
    public Entity spawnEntity(@NotNull World world, @NotNull Location location) {
        Entity entity = world.spawnFallingBlock(location, blockData);
        applyAttributes(entity);
        attributes.forEach(attributeData -> attributeData.apply(entity));
        return entity;
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        FallingBlock fallingBlock = (FallingBlock) entity;
        fallingBlock.setDropItem(dropItem);
        fallingBlock.setCancelDrop(cancelDrop);
        fallingBlock.setHurtEntities(hurtEntities);
        fallingBlock.setDamagePerBlock(damagePerBlock);
        fallingBlock.setMaxDamage(maxDamage);
    }
}
