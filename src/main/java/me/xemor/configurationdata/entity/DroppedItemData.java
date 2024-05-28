package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.ItemStackData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class DroppedItemData extends EntityData {

    private final ItemStackData stackData;

    public DroppedItemData(ConfigurationSection configurationSection) {
        super(configurationSection);
        ConfigurationSection itemSection = configurationSection.getConfigurationSection("item");
        if (itemSection == null) {
            ConfigurationData.getLogger().warning("Deprecated: " + configurationSection.getCurrentPath() + " has item properties, that are not within an item section! Please indent these within an item section.");
            stackData = new ItemStackData(configurationSection);
        } else {
            stackData = new ItemStackData(itemSection);
        }
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Item droppedItem = (Item) entity;
        droppedItem.setItemStack(stackData.getItem());
    }
}
