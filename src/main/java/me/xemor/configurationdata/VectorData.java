package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

public class VectorData {
    private final Vector vector;

    public VectorData(ConfigurationSection vectorSection) {
        vector = new Vector(vectorSection.getDouble("x"), vectorSection.getDouble("y"), vectorSection.getDouble("z"));
    }

    public Vector getVector() {
        return vector;
    }
}
