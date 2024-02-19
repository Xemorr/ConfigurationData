package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;

public class AxolotlData extends LivingEntityData {

    private Axolotl.Variant variant = null;

    public AxolotlData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        String variantStr = configurationSection.getString("variant");
        if (variantStr != null) {
            try {
                variant = Axolotl.Variant.valueOf(variantStr);
            } catch (IllegalArgumentException e) {
                ConfigurationData.getLogger().severe("You have entered an invalid variant at " + configurationSection.getCurrentPath() + ".variant");
            }
        }
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Axolotl axolotl = (Axolotl) entity;
        if (variant != null) {
            axolotl.setVariant(variant);
        }
    }
}
