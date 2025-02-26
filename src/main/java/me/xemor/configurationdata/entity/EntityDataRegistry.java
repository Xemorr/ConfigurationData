package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.concurrent.ConcurrentHashMap;

public class EntityDataRegistry {

    private static final ConcurrentHashMap<EntityType, Class<? extends EntityData>> entityTypeToDataClass = new ConcurrentHashMap<>();

    static {
        registerEntityData(EntityType.ARROW, ArrowData.class);
        registerEntityData(EntityType.AXOLOTL, AxolotlData.class);
        registerEntityData(EntityType.CREEPER, CreeperData.class);
        registerEntityData(EntityType.ITEM, DroppedItemData.class);
        registerEntityData(EntityType.EXPERIENCE_ORB, ExperienceOrbData.class);
        registerEntityData(EntityType.FALLING_BLOCK, FallingBlockData.class);
        registerEntityData(EntityType.HORSE, HorseData.class);
        registerEntityData(EntityType.SKELETON_HORSE, HorseData.class);
        registerEntityData(EntityType.ZOMBIE_HORSE, HorseData.class);
        registerEntityData(EntityType.LLAMA, LlamaData.class);
        registerEntityData(EntityType.TNT, PrimedTntData.class);
        registerEntityData(EntityType.SPECTRAL_ARROW, SpectralArrowData.class);
        registerEntityData(EntityType.POTION, PotionEntityData.class);
        registerEntityData(EntityType.RABBIT, RabbitData.class);
        registerEntityData(EntityType.TRIDENT, AbstractArrowData.class);
        registerEntityData(EntityType.WITHER_SKULL, WitherSkullData.class);
        registerEntityData(EntityType.WOLF, WolfData.class);
    }

    public static void registerEntityData(EntityType entityType, Class<? extends EntityData> entityDataClass) {
        entityTypeToDataClass.put(entityType, entityDataClass);
    }

    public static Class<? extends EntityData> getEntityDataClass(EntityType entityType) {
        Class<? extends EntityData> entityDataClass = entityTypeToDataClass.get(entityType);
        if (entityDataClass != null) {
            return entityDataClass;
        }

        Class<? extends Entity> entityClass = entityType.getEntityClass();
        if (entityClass == null) {
            return null;
        }

        if (LivingEntity.class.isAssignableFrom(entityClass)) {
            return LivingEntityData.class;
        } else {
            return EntityData.class;
        }
    }

    @FunctionalInterface
    public interface EntityDataConstructor {
        EntityData apply(ConfigurationSection configurationSection);
    }
}
