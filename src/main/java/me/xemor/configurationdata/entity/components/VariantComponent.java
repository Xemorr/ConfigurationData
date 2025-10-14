package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.EntityData;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private String variant = null;

    @Override
    public void apply(Entity entity, EntityData builderSoFar) {
        if (entity instanceof Cat cat) {
            cat.setCatType(Registry.CAT_VARIANT.getOrThrow(NamespacedKey.fromString(variant)));
        }
        else if (entity instanceof Chicken chicken) {
            chicken.setVariant(Registry.CHICKEN_VARIANT.getOrThrow(NamespacedKey.fromString(variant)));
        }
        else if (entity instanceof Cow cow) {
            cow.setVariant(Registry.COW_VARIANT.getOrThrow(NamespacedKey.fromString(variant)));
        }
        else if (entity instanceof Fox fox) {
            fox.setFoxType(Fox.Type.valueOf(variant));
        }
        else if (entity instanceof Frog frog) {
            frog.setVariant(Registry.FROG_VARIANT.getOrThrow(NamespacedKey.fromString(variant)));
        }
        else if (entity instanceof MushroomCow mushroomCow) {
            mushroomCow.setVariant(MushroomCow.Variant.valueOf(variant));
        }
        else if (entity instanceof Parrot parrot) {
            parrot.setVariant(Parrot.Variant.valueOf(variant));
        }
        else if (entity instanceof Pig pig) {
            pig.setVariant(Registry.PIG_VARIANT.getOrThrow(NamespacedKey.fromString(variant)));
        }
        else if (entity instanceof Salmon salmon) {
            salmon.setVariant(Salmon.Variant.valueOf(variant));
        }
        else if (entity instanceof Villager villager) {
            villager.setVillagerType(Registry.VILLAGER_TYPE.getOrThrow(NamespacedKey.fromString(variant)));
        }
        else if (entity instanceof Wolf wolf) {
            wolf.setVariant(Registry.WOLF_VARIANT.getOrThrow(NamespacedKey.fromString(variant)));
        }
    }
}
