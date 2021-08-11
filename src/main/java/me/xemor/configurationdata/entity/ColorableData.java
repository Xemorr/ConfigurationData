package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.DyeColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Shulker;
import org.bukkit.material.Colorable;

public class ColorableData extends ExtraData {

    private DyeColor dyeColor;

    public ColorableData(ConfigurationSection section) {
        super(section);
        String dyeColorStr = section.getString("color");
        try {
            dyeColor = DyeColor.valueOf(dyeColorStr);
        } catch (IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("You have entered an invalid color at " + section.getCurrentPath() + ".color");
        }
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof Colorable) {
            Colorable colorable = (Colorable) entity;
            colorable.setColor(dyeColor);
        }
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }
}
