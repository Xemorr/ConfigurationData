package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

public class EquipmentData {

    @JsonPropertyWithDefault
    @JsonAlias({"helmet"})
    private final EquipableData head = null;
    @JsonPropertyWithDefault
    @JsonAlias({"chestplate"})
    private final EquipableData chest = null;
    @JsonPropertyWithDefault
    @JsonAlias({"leggings"})
    private final EquipableData legs = null;
    @JsonPropertyWithDefault
    @JsonAlias({"feet"})
    private final EquipableData feet = null;
    @JsonPropertyWithDefault
    @JsonAlias("hand")
    private final EquipableData mainhand = null;
    @JsonPropertyWithDefault
    private final EquipableData offhand = null;

    public EquipableData getHead() {
        return head;
    }

    public EquipableData getChest() {
        return chest;
    }

    public EquipableData getLegs() {
        return legs;
    }

    public EquipableData getFeet() {
        return feet;
    }

    public EquipableData getMainHand() {
        return mainhand;
    }

    public EquipableData getOffHand() {
        return offhand;
    }

    public final void applyEquipment(LivingEntity livingEntity) {
        EntityEquipment equipment = livingEntity.getEquipment();
        if (equipment == null) return;
        if (head != null) {
            equipment.setHelmet(head.item(), true);
            equipment.setHelmetDropChance(head.droprate());
        }
        if (chest != null) {
            equipment.setChestplate(chest.item(), true);
            equipment.setChestplateDropChance(chest.droprate());
        }
        if (legs != null) {
            equipment.setLeggings(legs.item(), true);
            equipment.setLeggingsDropChance(legs.droprate());
        }
        if (feet != null) {
            equipment.setBoots(feet.item(), true);
            equipment.setBootsDropChance(feet.droprate());
        }
        if (mainhand != null) {
            equipment.setItemInMainHand(mainhand.item(), true);
            equipment.setItemInMainHandDropChance(mainhand.droprate());
        }
        if (offhand != null) {
            equipment.setItemInOffHand(offhand.item(), true);
            equipment.setItemInOffHandDropChance(offhand.droprate());
        }
    }
}
