package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public record LeatherArmorColor(int red, int green, int blue) {

    @JsonCreator
    public LeatherArmorColor(Integer red, Integer green, Integer blue) {
        this(red == null ? -1 : red, green == null ? -1 : green, blue == null ? -1 : blue);
    }

    public void handleLeatherArmor(LeatherArmorMeta meta) {
        Color color;
        if (red < 0 || blue < 0 || green < 0) {
            color = null;
        } else {
            color = Color.fromRGB(red, green, blue);
        }
        meta.setColor(color);
    }

}
