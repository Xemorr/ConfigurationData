package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;

public class RabbitData extends LivingEntityData {

    private Rabbit.Type variant = null;

    public RabbitData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

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
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Rabbit rabbit = (Rabbit) entity;
        if (variant != null) {
            rabbit.setRabbitType(variant);
        }
    }
}
