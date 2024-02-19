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
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        stackData = new ItemStackData(configurationSection);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Item droppedItem = (Item) entity;
        droppedItem.setItemStack(stackData.getItem());
    }
}
