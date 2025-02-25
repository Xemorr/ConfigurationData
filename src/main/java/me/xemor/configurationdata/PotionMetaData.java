package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class PotionMetaData {

    @JsonProperty
    private final PotionType type = PotionType.SWIFTNESS;
    @JsonProperty
    private final boolean extended = false;
    @JsonProperty
    private final boolean upgraded = false;

    public void applyPotionData(PotionMeta meta) {
        if (extended || upgraded) ConfigurationData.getLogger().severe("You can no longer specify upgraded or extended on Potions! Please remove this, and instead do LONG_SWIFTNESS or STRONG_SWIFTNESS as type");
        meta.setBasePotionType(type);
    }

}
