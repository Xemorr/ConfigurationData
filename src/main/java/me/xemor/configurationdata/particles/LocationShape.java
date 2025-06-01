package me.xemor.configurationdata.particles;

import org.bukkit.Location;

import java.util.List;

public interface LocationShape {
    List<Location> getWireframe(Location location); //Wire frame represents a list of locations that particles should be spawned at.
}
