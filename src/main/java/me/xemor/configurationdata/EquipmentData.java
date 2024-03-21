package me.xemor.configurationdata;

import me.xemor.configurationdata.ItemStackData;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class EquipmentData {

    private ItemStack helmet = null;
    private ItemStack chestplate = null;
    private ItemStack leggings = null;
    private ItemStack boots = null;
    private ItemStack mainHand = null;
    private ItemStack offHand = null;
    private float helmetDropRate;
    private float chestplateDropRate;
    private float leggingsDropRate;
    private float bootsDropRate;
    private float mainhandDropRate;
    private float offhandDropRate;

    public EquipmentData(ConfigurationSection configurationSection) {
        ConfigurationSection helmetSection = configurationSection.getConfigurationSection("helmet");
        ConfigurationSection chestplateSection = configurationSection.getConfigurationSection("chestplate");
        ConfigurationSection leggingsSection = configurationSection.getConfigurationSection("leggings");
        ConfigurationSection bootsSection = configurationSection.getConfigurationSection("boots");
        ConfigurationSection mainHandSection = configurationSection.getConfigurationSection("mainhand");
        ConfigurationSection offHandSection = configurationSection.getConfigurationSection("offhand");
        if (helmetSection != null) {
            ItemStackData helmetData = new ItemStackData(helmetSection, "AIR");
            this.helmet = helmetData.getItem();
            helmetDropRate = (float) helmetSection.getDouble("droprate", 0.0);
        }
        if (chestplateSection != null) {
            ItemStackData chestplateData = new ItemStackData(chestplateSection, "AIR");
            this.chestplate = chestplateData.getItem();
            chestplateDropRate = (float) chestplateSection.getDouble("droprate", 0.0);
        }
        if (leggingsSection != null) {
            ItemStackData leggingsData = new ItemStackData(leggingsSection, "AIR");
            this.leggings = leggingsData.getItem();
            leggingsDropRate = (float) leggingsSection.getDouble("droprate", 0.0);
        }
        if (bootsSection != null) {
            ItemStackData bootsData = new ItemStackData(bootsSection, "AIR");
            this.boots = bootsData.getItem();
            bootsDropRate = (float) bootsSection.getDouble("droprate", 0.0);
        }
        if (mainHandSection != null) {
            ItemStackData mainHandData = new ItemStackData(mainHandSection, "AIR");
            this.mainHand = mainHandData.getItem();
            mainhandDropRate = (float) mainHandSection.getDouble("droprate", 0.0);
        }
        if (offHandSection != null) {
            ItemStackData offHandData = new ItemStackData(offHandSection, "AIR");
            this.offHand = offHandData.getItem();
            offhandDropRate = (float) offHandSection.getDouble("droprate", 0.0);
        }
    }

    public EquipmentData() {
        helmet = new ItemStack(Material.AIR);
        chestplate = new ItemStack(Material.AIR);
        leggings = new ItemStack(Material.AIR);
        boots = new ItemStack(Material.AIR);
        mainHand = new ItemStack(Material.AIR);
        offHand = new ItemStack(Material.AIR);
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getMainHand() {
        return mainHand;
    }

    public ItemStack getOffHand() {
        return offHand;
    }

    public float getHelmetDropRate() {
        return helmetDropRate;
    }

    public float getChestplateDropRate() {
        return chestplateDropRate;
    }

    public float getLeggingsDropRate() {
        return leggingsDropRate;
    }

    public float getBootsDropRate() {
        return bootsDropRate;
    }

    public float getMainHandDropRate() {
        return mainhandDropRate;
    }

    public float getOffHandDropRate() {
        return offhandDropRate;
    }

}
