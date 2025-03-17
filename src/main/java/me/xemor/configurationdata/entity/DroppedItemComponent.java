package me.xemor.configurationdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.ItemStackData;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DroppedItemComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private ItemStackData stackData = null;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        Item droppedItem = (Item) entity;
        if (stackData != null) droppedItem.setItemStack(stackData.item());
    }
}
