package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.ItemStackData;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;

public class HorseData extends ExtraData {

    private ItemStack armor = null;
    private boolean hasSaddle;
    private Horse.Color color;
    private Horse.Style style;
    private int tamingDifficulty;
    private double jumpStrength;
    private boolean isCarryingChest;

    public HorseData(ConfigurationSection configurationSection) {
        super(configurationSection);
        ConfigurationSection armorSection = configurationSection.getConfigurationSection("armorSection");
        if (armorSection != null) {
            armor = new ItemStackData(armorSection).getItem();
        }
        hasSaddle = configurationSection.getBoolean("hasSaddle", false);
        String colorStr = configurationSection.getString("color", "CHESTNUT").toUpperCase();
        try {
            color = Horse.Color.valueOf(colorStr);
        } catch (IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("Invalid color entered for horse " + colorStr);
        }
        String styleStr = configurationSection.getString("style", "NONE").toUpperCase();
        try {
            style = Horse.Style.valueOf(styleStr);
        } catch(IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("Invalid style entered for horse " + styleStr);
        }
        this.tamingDifficulty = configurationSection.getInt("tamingDifficulty", 1);
        this.jumpStrength = configurationSection.getDouble("jumpStrength", 0.7);
        this.isCarryingChest = configurationSection.getBoolean("hasChest", false);
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof AbstractHorse) {
            AbstractHorse horse = (AbstractHorse) entity;
            horse.getInventory().setSaddle(hasSaddle ? new ItemStack(Material.SADDLE) : null);
            horse.setMaxDomestication(tamingDifficulty);
            horse.setJumpStrength(jumpStrength);
        }
        if (entity instanceof Horse) {
            Horse horse = (Horse) entity;
            horse.getInventory().setArmor(armor);
            horse.setColor(color);
            horse.setStyle(style);
        }
        else if (entity instanceof ChestedHorse) {
            ChestedHorse chestedHorse = (ChestedHorse) entity;
            chestedHorse.setCarryingChest(isCarryingChest);
        }
    }
}
