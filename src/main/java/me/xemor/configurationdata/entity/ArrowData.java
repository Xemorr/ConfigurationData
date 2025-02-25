package me.xemor.configurationdata.entity;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ArrowData extends AbstractArrowData {

    private PotionType potionType;
    private PotionData potionData;

    public ArrowData(ConfigurationSection configurationSection) {
        super(configurationSection);

        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potionSection != null) {
            PotionType type = PotionType.valueOf(potionSection.getString("type", "UNCRAFTABLE"));
            if (getMinorVersion() <= 21 || potionSection.contains("extended")) {
                boolean extended = potionSection.getBoolean("extended");
                boolean upgraded = potionSection.getBoolean("upgraded");
                potionData = new PotionData(type, extended, upgraded);
            }
        } else {
            potionData = null;
        }
    }

    public int getMinorVersion() {
        return Integer.parseInt(Bukkit.getServer().getBukkitVersion().split("-")[0].split("\\.")[1]);
    }

    @Override
    public void applyExtraMetadata(Entity entity) {
        super.applyExtraMetadata(entity);

        Arrow arrow = (Arrow) entity;
        if (potionData != null && getMinorVersion() <= 21 && potionData != null) {
            arrow.setBasePotionData(potionData);
        }
        else {
            arrow.setBasePotionType(potionType);
        }
    }
}
