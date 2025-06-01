package me.xemor.configurationdata.particles.shapes;

import com.fasterxml.jackson.annotation.JsonAlias;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import me.xemor.configurationdata.particles.LocationShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class Point extends Shape implements EntityShape, LocationShape {
    //Simplest particle type. Wireframe contains a single particle with random coordinates within config defined bounds.
    //Example of the shape produced can be viewed here: https://gyazo.com/c408559c28c76ea20667b0bb501fc1a4
    //Example above shows point with offset to 0 and spread at 0.5.

    @JsonPropertyWithDefault
    private double spread = 0;
    @JsonPropertyWithDefault
    @JsonAlias("offset")
    private double yOffset = 0;

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        return getWireframe(location);
    }

    public double getSpread() {
        return spread;
    }

    @Override
    public List<Location> getWireframe(Location location) {
        List<Location> wireframe = new ArrayList<>();
        double xCoordinate = location.getX() + (2 * spread * Math.random() - spread) - 0.2;
        double yCoordinate = location.getY() + (2 * spread * Math.random() - spread) + yOffset;
        double zCoordinate = location.getZ() + (2 * spread * Math.random() - spread) - 0.2;
        Location particleLocation = new Location(location.getWorld(), xCoordinate, yCoordinate, zCoordinate);
        wireframe.add(particleLocation);
        return wireframe;
    }
}
