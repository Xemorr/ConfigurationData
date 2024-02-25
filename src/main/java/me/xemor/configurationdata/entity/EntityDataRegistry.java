package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.concurrent.ConcurrentHashMap;

public class EntityDataRegistry {

    private static final ConcurrentHashMap<EntityType, EntityDataConstructor> entityTypeToDataClass = new ConcurrentHashMap<>();

    static {
        registerEntityData(EntityType.AXOLOTL, AxolotlData::new);
        registerEntityData(EntityType.CREEPER, CreeperData::new);
        registerEntityData(EntityType.DROPPED_ITEM, DroppedItemData::new);
        registerEntityData(EntityType.EXPERIENCE_ORB, ExperienceOrbData::new);
        registerEntityData(EntityType.FALLING_BLOCK, FallingBlockData::new);
        registerEntityData(EntityType.HORSE, HorseData::new);
        registerEntityData(EntityType.SKELETON_HORSE, HorseData::new);
        registerEntityData(EntityType.ZOMBIE_HORSE, HorseData::new);
        registerEntityData(EntityType.SPLASH_POTION, PotionEntityData::new);
        registerEntityData(EntityType.RABBIT, RabbitData::new);
        registerEntityData(EntityType.WITHER_SKULL, WitherSkullData::new);
        registerEntityData(EntityType.WOLF, WolfData::new);
    }

    public static void registerEntityData(EntityType entityType, EntityDataConstructor entityDataConstructor) {
        entityTypeToDataClass.put(entityType, entityDataConstructor);
    }

    public static EntityDataConstructor getConstructor(EntityType entityType) {
        EntityDataConstructor dataConstructor = entityTypeToDataClass.get(entityType);
        if (dataConstructor != null) {
            return dataConstructor;
        }

        Class<? extends Entity> entityClass = entityType.getEntityClass();
        if (entityClass == null) {
            return null;
        }

        if (LivingEntity.class.isAssignableFrom(entityClass)) {
            return LivingEntityData::new;
        } else {
            return EntityData::new;
        }
    }

    @FunctionalInterface
    public interface EntityDataConstructor {
        EntityData apply(ConfigurationSection configurationSection);
    }
}
