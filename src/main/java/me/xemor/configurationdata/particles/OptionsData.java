package me.xemor.configurationdata.particles;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import org.bukkit.Color;

import java.util.ArrayList;
import java.util.List;

public class OptionsData { // For particles with Particle.DustOptions nested class. Used for REDSTONE particle type.

    @JsonPropertyWithDefault
    private List<Color> colours = new ArrayList<>();
    @JsonPropertyWithDefault
    private float scale = 1f;

    public float getScale() {
        return scale;
    }

    public List<Color> getColours() {
        return colours;
    }
}
