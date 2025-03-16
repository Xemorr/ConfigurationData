package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ItemStackData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class DroppedItemComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private final ItemStackData stackData = null;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Item droppedItem = (Item) entity;
        if (stackData != null) droppedItem.setItemStack(stackData.item());
    }
}
