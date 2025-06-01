package me.xemor.configurationdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.xemor.configurationdata.deserializers.*;
import me.xemor.configurationdata.deserializers.text.ComponentDeserializer;
import me.xemor.configurationdata.deserializers.text.RegistryDeserializer;
import me.xemor.configurationdata.deserializers.text.RegistryKeyDeserializer;
import me.xemor.configurationdata.entity.EntityData;
import me.xemor.configurationdata.particles.shapes.Shapes;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
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
                .addDeserializer(Particle.class, new RegistryDeserializer<>(Registry.PARTICLE_TYPE))
                .addDeserializer(BlockData.class, new BlockDataDeserializer())
                .addDeserializer(Vector.class, new BukkitVectorDeserializer())
                .addDeserializer(ItemStack.class, new ItemStackDeserializer())
                .addDeserializer(EntityData.class, new EntityDataDeserializer())
                .addDeserializer(Component.class, new ComponentDeserializer())
                .addDeserializer(Color.class, new ColorDeserializer());
        simpleModule
                .addKeyDeserializer(Attribute.class, new RegistryKeyDeserializer<>(Registry.ATTRIBUTE))
                .addKeyDeserializer(Enchantment.class, new RegistryKeyDeserializer<>(Registry.ENCHANTMENT))
                .addKeyDeserializer(EntityType.class, new RegistryKeyDeserializer<>(Registry.ENTITY_TYPE))
                .addKeyDeserializer(Material.class, new RegistryKeyDeserializer<>(Registry.MATERIAL))
                .addKeyDeserializer(PotionEffectType.class, new RegistryKeyDeserializer<>(Registry.EFFECT))
                .addKeyDeserializer(PotionType.class, new RegistryKeyDeserializer<>(Registry.POTION))
                .addKeyDeserializer(Sound.class, new RegistryKeyDeserializer<>(Registry.SOUNDS))
                .addKeyDeserializer(TrimMaterial.class, new RegistryKeyDeserializer<>(Registry.TRIM_MATERIAL))
                .addKeyDeserializer(TrimPattern.class, new RegistryKeyDeserializer<>(Registry.TRIM_PATTERN))
                .addKeyDeserializer(Particle.class, new RegistryKeyDeserializer<>(Registry.PARTICLE_TYPE));
        objectMapper.registerModule(simpleModule);
        objectMapper.registerSubtypes(Shapes.getNamedSubTypes());
        return objectMapper;
    }

    public static int getArbitraryClock() {
        return (int) Math.abs((System.currentTimeMillis() / 50L));
    }

    public static void setup(JavaPlugin newPlugin) {
        plugin = newPlugin;
    }
}
