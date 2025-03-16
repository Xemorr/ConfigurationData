package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class EntityComponentRegistry {

    private static final List<EntityClassToComponentPair> entityTypeToDataClass = new ArrayList<>();

    static {
        registerEntityData(EntityType.ARROW, ArrowComponentComponent::new);
        registerEntityData(EntityType.AXOLOTL, AxolotlComponent::new);
        registerEntityData(EntityType.CREEPER, CreeperComponent::new);
        registerEntityData(EntityType.ITEM, DroppedItemComponent::new);
        registerEntityData(EntityType.EXPERIENCE_ORB, ExperienceOrbComponent::new);
        registerEntityData(EntityType.FALLING_BLOCK, FallingBlockComponent::new);
        registerEntityData(EntityType.HORSE, HorseComponent::new);
        registerEntityData(EntityType.SKELETON_HORSE, HorseComponent::new);
        registerEntityData(EntityType.ZOMBIE_HORSE, HorseComponent::new);
        registerEntityData(EntityType.LLAMA, LlamaComponent::new);
        registerEntityData(EntityType.TNT, PrimedTntComponent::new);
        registerEntityData(EntityType.SPECTRAL_ARROW, SpectralArrowComponent::new);
        registerEntityData(EntityType.POTION, PotionEntityComponent::new);
        registerEntityData(EntityType.RABBIT, RabbitComponent::new);
        registerEntityData(EntityType.TRIDENT, AbstractArrowComponent::new);
        registerEntityData(EntityType.WITHER_SKULL, WitherSkullComponent::new);
        registerEntityData(EntityType.WOLF, WolfComponent::new);
    }

    public static void registerEntityData(Class<Entity> inheritsFrom, Class<EntityComponent> entityComponentDataClass) {
        entityTypeToDataClass.add(new EntityClassToComponentPair(inheritsFrom, entityComponentDataClass));
    }

    public static List<Class<EntityComponent>> getEntityComponentDataClasses(Class<? extends Entity> inheritsFrom) {
        return entityTypeToDataClass.stream()
                .filter((pair) -> inheritsFrom.isAssignableFrom(pair.clazz))
                .map((pair) -> pair.componentClazz)
                .toList();
    }

    private record EntityClassToComponentPair(Class<Entity> clazz, Class<EntityComponent> componentClazz) {}
}
