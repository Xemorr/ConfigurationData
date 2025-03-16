package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;

public class FallingBlockComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final BlockData blockData = Material.STONE.createBlockData();
    @JsonPropertyWithDefault
    private final boolean dropItem = true;
    @JsonPropertyWithDefault
    private final boolean cancelDrop = false;
    @JsonPropertyWithDefault
    private final boolean hurtEntities = false;
    @JsonPropertyWithDefault
    private final float damagePerBlock = 2;
    @JsonPropertyWithDefault
    private final int maxDamage = 40;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        FallingBlock fallingBlock = (FallingBlock) entity;
        fallingBlock.setDropItem(dropItem);
        fallingBlock.setCancelDrop(cancelDrop);
        fallingBlock.setHurtEntities(hurtEntities);
        fallingBlock.setDamagePerBlock(damagePerBlock);
        fallingBlock.setMaxDamage(maxDamage);
    }
}
