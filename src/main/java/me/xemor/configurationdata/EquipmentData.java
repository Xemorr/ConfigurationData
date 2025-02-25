package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
