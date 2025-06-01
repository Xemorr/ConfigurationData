package me.xemor.configurationdata.particles.shapes;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class Halo extends Shape implements EntityShape {
    //Example of the shape produced can be viewed here: https://gyazo.com/22b14f9dafc91c9bd8112ffe9b1aa888

    @JsonPropertyWithDefault
    private double radius = 0.3;
    @JsonPropertyWithDefault
    private double height = 0.3;
    @JsonPropertyWithDefault
    private double resolution = 32;

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        double yCoordinate = location.getY() + 2 + height; // By default uses player head height as designed to sit on top of head
        for (double radians = 0; radians < 2*Math.PI; radians+=(2*Math.PI/resolution)) {
            double xCoordinate = location.getX() + (radius * Math.sin(radians));
            double zCoordinate = location.getZ() + (radius * Math.cos(radians));
            Location particleLocation = new Location(entity.getWorld(), xCoordinate, yCoordinate, zCoordinate);
            wireframe.add(particleLocation);
        }
        return wireframe;
    }
}
