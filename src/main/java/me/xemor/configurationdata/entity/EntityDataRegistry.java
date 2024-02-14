package me.xemor.configurationdata.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.concurrent.ConcurrentHashMap;

public class EntityDataRegistry {

    private static final ConcurrentHashMap<EntityType, Class<? extends EntityData>> entityTypeToDataClass = new ConcurrentHashMap<>();

    static {
        registerEntityData(EntityType.AXOLOTL, AxolotlData.class);
        registerEntityData(EntityType.CREEPER, CreeperData.class);
        registerEntityData(EntityType.DROPPED_ITEM, DroppedItemData.class);
        registerEntityData(EntityType.HORSE, HorseData.class);
        registerEntityData(EntityType.SKELETON_HORSE, HorseData.class);
        registerEntityData(EntityType.ZOMBIE_HORSE, HorseData.class);
        registerEntityData(EntityType.SPLASH_POTION, PotionEntityData.class);
        registerEntityData(EntityType.RABBIT, RabbitData.class);
        registerEntityData(EntityType.WITHER_SKULL, WitherSkullData.class);
        registerEntityData(EntityType.WOLF, WolfData.class);
    }

    public static void registerEntityData(EntityType entityType, Class<? extends EntityData> entityDataClass) {
        entityTypeToDataClass.put(entityType, entityDataClass);
    }

    public static Class<? extends EntityData> getClass(EntityType entityType) {
        Class<? extends EntityData> dataClass = entityTypeToDataClass.get(entityType);
        if (dataClass != null) {
            return dataClass;
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
}
