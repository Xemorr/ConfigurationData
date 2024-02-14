package me.xemor.configurationdata.entity.attribute;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;

public class SizeData extends EntityAttributeData {

    private final int size;

    public SizeData(ConfigurationSection configurationSection) {
        super(configurationSection);
        size = configurationSection.getInt("size");
    }

    @Override
    public void apply(Entity entity) {
        if (entity instanceof Slime) {
            Slime slime = (Slime) entity;
            slime.setSize(size);
        }
        else if (entity instanceof Phantom) {
            Phantom phantom = (Phantom) entity;
            phantom.setSize(size);
        }
    }
}
