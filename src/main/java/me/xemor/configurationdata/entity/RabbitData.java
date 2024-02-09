package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;

public class RabbitData extends ExtraData {

    private Rabbit.Type variant = null;

    public RabbitData(ConfigurationSection configurationSection) {
        super(configurationSection);
        String variantStr = configurationSection.getString("variant");
        if (variantStr != null) {
            try {
                variant = Rabbit.Type.valueOf(variantStr);
            } catch (IllegalArgumentException e) {
                ConfigurationData.getLogger().severe("You have entered an invalid variant at " + configurationSection.getCurrentPath() + ".variant");
            }
        }
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof Rabbit) {
            Rabbit rabbit = (Rabbit) entity;
            if (variant != null) {
                rabbit.setRabbitType(variant);
            }
        }
    }
}
