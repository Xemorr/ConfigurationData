package me.xemor.configurationdata.entity.attribute;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.DyeColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.material.Colorable;

public class ColorableData extends EntityAttributeData {

    private DyeColor dyeColor;

    public ColorableData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        String dyeColorStr = configurationSection.getString("color");
        try {
            dyeColor = DyeColor.valueOf(dyeColorStr);
        } catch (IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("You have entered an invalid color at " + configurationSection.getCurrentPath() + ".color");
        }
    }

    @Override
    public void apply(Entity entity) {
        Colorable colorable = (Colorable) entity;
        colorable.setColor(dyeColor);
    }
}
