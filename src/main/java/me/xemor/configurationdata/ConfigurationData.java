package me.xemor.configurationdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.xemor.configurationdata.deserializers.BlockDataDeserializer;
import me.xemor.configurationdata.deserializers.BukkitVectorDeserializer;
import me.xemor.configurationdata.deserializers.ItemStackDeserializer;
import me.xemor.configurationdata.deserializers.NewEntityDataDeserializer;
import me.xemor.configurationdata.deserializers.text.*;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.Material;
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
                .addDeserializer(Attribute.class, new AttributeDeserializer())
                .addDeserializer(Enchantment.class, new EnchantmentDeserializer())
                .addDeserializer(EntityType.class, new EntityTypeDeserializer())
                .addDeserializer(Material.class, new MaterialDeserializer())
                .addDeserializer(PotionEffectType.class, new PotionEffectTypeDeserializer())
                .addDeserializer(PotionType.class, new PotionTypeDeserializer())
                .addDeserializer(Sound.class, new SoundDeserializer())
                .addDeserializer(TrimMaterial.class, new TrimMaterialDeserializer())
                .addDeserializer(TrimPattern.class, new TrimPatternDeserializer())
                .addDeserializer(BlockData.class, new BlockDataDeserializer())
                .addDeserializer(Vector.class, new BukkitVectorDeserializer())
                .addDeserializer(ItemStack.class, new ItemStackDeserializer())
                .addDeserializer(NewEntityData.class, new NewEntityDataDeserializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    public static void setup(JavaPlugin newPlugin) {
        plugin = newPlugin;
    }
}
