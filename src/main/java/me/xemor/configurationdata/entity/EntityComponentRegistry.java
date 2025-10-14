package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.entity.components.*;
import org.bukkit.entity.*;
import org.bukkit.material.Colorable;

import java.util.ArrayList;
import java.util.List;

public class EntityComponentRegistry {

    private static final List<EntityClassToComponentPair> entityTypeToDataClass = new ArrayList<>();

    static {
        registerEntityData(Arrow.class, ArrowComponent.class);
        registerEntityData(Axolotl.class, AxolotlComponent.class);
        registerEntityData(Cat.class, VariantComponent.class);
        registerEntityData(Chicken.class, VariantComponent.class);
        registerEntityData(Cow.class, VariantComponent.class);
        registerEntityData(Creeper.class, CreeperComponent.class);
        registerEntityData(Item.class, DroppedItemComponent.class);
        registerEntityData(ExperienceOrb.class, ExperienceOrbComponent.class);
        registerEntityData(FallingBlock.class, FallingBlockComponent.class);
        registerEntityData(Fox.class, VariantComponent.class);
        registerEntityData(Frog.class, VariantComponent.class);
        registerEntityData(AbstractHorse.class, AbstractHorseComponent.class);
        registerEntityData(Horse.class, HorseComponent.class);
        registerEntityData(ChestedHorse.class, ChestedHorseComponent.class);
        registerEntityData(Llama.class, LlamaComponent.class);
        registerEntityData(MushroomCow.class, VariantComponent.class);
        registerEntityData(Parrot.class, VariantComponent.class);
        registerEntityData(Pig.class, VariantComponent.class);
        registerEntityData(TNTPrimed.class, PrimedTntComponent.class);
        registerEntityData(SpectralArrow.class, SpectralArrowComponent.class);
        registerEntityData(ThrownPotion.class, PotionEntityComponent.class);
        registerEntityData(Rabbit.class, RabbitComponent.class);
        registerEntityData(Trident.class, AbstractArrowComponent.class);
        registerEntityData(WitherSkull.class, WitherSkullComponent.class);
        registerEntityData(Wolf.class, WolfComponent.class);
        registerEntityData(Ageable.class, AgeableComponent.class);
        registerEntityData(Colorable.class, ColorableComponent.class);
        registerEntityData(Explosive.class, ExplosiveComponent.class);
        registerEntityData(Salmon.class, VariantComponent.class);
        registerEntityData(Slime.class, SizeComponent.class);
        registerEntityData(Phantom.class, SizeComponent.class);
        registerEntityData(ThrowableProjectile.class, ThrowableProjectileComponent.class);
        registerEntityData(PiglinAbstract.class, ZombifiableComponent.class);
        registerEntityData(Hoglin.class, ZombifiableComponent.class);
        registerEntityData(LivingEntity.class, LivingEntityComponent.class);
        registerEntityData(Villager.class, VariantComponent.class);
    }

    public static void registerEntityData(Class inheritsFrom, Class<? extends EntityComponent> entityComponentDataClass) {
        entityTypeToDataClass.add(new EntityClassToComponentPair(inheritsFrom, entityComponentDataClass));
    }

    public static List<? extends Class<? extends EntityComponent>> getEntityComponentDataClasses(Class<? extends Entity> inheritsFrom) {
        return entityTypeToDataClass.stream()
                .filter((pair) -> pair.clazz.isAssignableFrom(inheritsFrom))
                .map((pair) -> pair.componentClazz)
                .toList();
    }

    private record EntityClassToComponentPair(Class clazz, Class<? extends EntityComponent> componentClazz) {}
}
