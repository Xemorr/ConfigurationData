package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FallingBlockComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private BlockData blockData = Material.STONE.createBlockData();
    @JsonPropertyWithDefault
    private boolean dropItem = true;
    @JsonPropertyWithDefault
    private boolean cancelDrop = false;
    @JsonPropertyWithDefault
    private boolean hurtEntities = false;
    @JsonPropertyWithDefault
    private float damagePerBlock = 2;
    @JsonPropertyWithDefault
    private int maxDamage = 40;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        FallingBlock fallingBlock = (FallingBlock) entity;
        fallingBlock.setDropItem(dropItem);
        fallingBlock.setCancelDrop(cancelDrop);
        fallingBlock.setHurtEntities(hurtEntities);
        fallingBlock.setDamagePerBlock(damagePerBlock);
        fallingBlock.setMaxDamage(maxDamage);
    }
}
