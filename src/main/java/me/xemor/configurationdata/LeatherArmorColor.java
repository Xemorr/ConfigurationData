package me.xemor.configurationdata;

import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorColor {

    @JsonPropertyWithDefault
    private int red = -1;
    @JsonPropertyWithDefault
    private int green = - 1;
    @JsonPropertyWithDefault
    private int blue = -1;

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
