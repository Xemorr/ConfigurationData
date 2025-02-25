package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

public class EquipmentData {

    @JsonProperty("head")
    @JsonAlias({"helmet"})
    private final EquipableData head = null;
    @JsonProperty("chest")
    @JsonAlias({"chestplate"})
    private final EquipableData chest = null;
    @JsonProperty("legs")
    @JsonAlias({"leggings"})
    private final EquipableData legs = null;
    @JsonProperty("boots")
    @JsonAlias({"feet"})
    private final EquipableData feet = null;
    @JsonProperty("mainhand")
    @JsonAlias("hand")
    private final EquipableData mainhand = null;
    @JsonProperty("offhand")
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
        equipment.setHelmet(head.item(), true);
        equipment.setHelmetDropChance(head.droprate());
        equipment.setChestplate(chest.item(), true);
        equipment.setChestplateDropChance(chest.droprate());
        equipment.setLeggings(legs.item(), true);
        equipment.setLeggingsDropChance(legs.droprate());
        equipment.setBoots(feet.item(), true);
        equipment.setBootsDropChance(feet.droprate());
        equipment.setItemInMainHand(mainhand.item(), true);
        equipment.setItemInMainHandDropChance(mainhand.droprate());
        equipment.setItemInOffHand(offhand.item(), true);
        equipment.setItemInOffHandDropChance(offhand.droprate());
    }
}
