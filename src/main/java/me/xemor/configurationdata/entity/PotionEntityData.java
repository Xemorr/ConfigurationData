package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.ItemStackData;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;

public class PotionEntityData extends EntityData {

    private final ItemStack potion;

    public PotionEntityData(ConfigurationSection configurationSection) {
        super(configurationSection);
        ConfigurationSection potionSection = configurationSection.getConfigurationSection("potion");
        if (potionSection != null) {
            potion = new ItemStackData(potionSection).getItem();
        }
        else {
            ConfigurationData.getLogger().warning("There was no extra data specified for Potion. " + configurationSection.getCurrentPath());
            potion = new ItemStack(Material.SPLASH_POTION);
        }
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        ThrownPotion thrownPotion = (ThrownPotion) entity;
        thrownPotion.setItem(potion);
    }
}
