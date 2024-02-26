package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ArrowData extends AbstractArrowData {

    private final PotionData potionData;

    public ArrowData(ConfigurationSection configurationSection) {
        super(configurationSection);

        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potionSection != null) {
            PotionType type = PotionType.valueOf(potionSection.getString("type", "UNCRAFTABLE"));
            boolean extended = potionSection.getBoolean("extended");
            boolean upgraded = potionSection.getBoolean("upgraded");

            potionData = new PotionData(type, extended, upgraded);
        } else {
            potionData = null;
        }
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Arrow arrow = (Arrow) entity;
        if (potionData != null) {
            arrow.setBasePotionData(potionData);
        }
    }
}
