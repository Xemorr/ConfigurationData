package me.xemor.configurationdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.xemor.configurationdata.deserializers.BlockDataDeserializer;
import me.xemor.configurationdata.deserializers.BukkitVectorDeserializer;
import me.xemor.configurationdata.deserializers.ItemStackDeserializer;
import me.xemor.configurationdata.deserializers.EntityDataDeserializer;
import me.xemor.configurationdata.deserializers.text.*;
import me.xemor.configurationdata.entity.EntityData;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

public class ConfigurationData {

    private static Logger logger;
    private static JavaPlugin plugin;

    static {
        logger = Logger.getLogger("XemorConfiguration");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static JavaPlugin getPlugin() { return plugin; }

    public static ObjectMapper setupObjectMapperForConfigurationData(ObjectMapper objectMapper) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule
                .addDeserializer(Attribute.class, new RegistryDeserializer<>(Registry.ATTRIBUTE))
                .addDeserializer(Enchantment.class, new RegistryDeserializer<>(Registry.ENCHANTMENT))
                .addDeserializer(EntityType.class, new RegistryDeserializer<>(Registry.ENTITY_TYPE))
                .addDeserializer(Material.class, new RegistryDeserializer<>(Registry.MATERIAL))
                .addDeserializer(PotionEffectType.class, new RegistryDeserializer<>(Registry.EFFECT))
                .addDeserializer(PotionType.class, new RegistryDeserializer<>(Registry.POTION))
                .addDeserializer(Sound.class, new RegistryDeserializer<>(Registry.SOUNDS))
                .addDeserializer(TrimMaterial.class, new RegistryDeserializer<>(Registry.TRIM_MATERIAL))
                .addDeserializer(TrimPattern.class, new RegistryDeserializer<>(Registry.TRIM_PATTERN))
                .addDeserializer(BlockData.class, new BlockDataDeserializer())
                .addDeserializer(Vector.class, new BukkitVectorDeserializer())
                .addDeserializer(ItemStack.class, new ItemStackDeserializer())
                .addDeserializer(EntityData.class, new EntityDataDeserializer())
                .addDeserializer(Component.class, new ComponentDeserializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    public static void setup(JavaPlugin newPlugin) {
        plugin = newPlugin;
    }
}
