package me.xemor.configurationdata.particles.shapes;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Ears extends Shape implements EntityShape {
    //Essentially creates two mirrored circles parallel to the z-axis

    @JsonPropertyWithDefault
    private double height = 0.2;
    @JsonPropertyWithDefault
    private double frequency = 0.05;
    @JsonPropertyWithDefault
    private double size = 1;
    @JsonPropertyWithDefault
    private double spread = 0.2;

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        Vector playerDirection = entity.getEyeLocation().getDirection();
        Vector perpendicularVector1 = getPerpendicularVector(playerDirection);
        Vector perpendicularVector2 = getPerpendicularVector(playerDirection);
        for (double theta = 0; theta < Math.PI; theta += frequency) {
            double radius = size * Math.cos(theta);
            double horizontalOffset = radius * Math.cos(theta) + spread;
            double verticalOffset = radius * Math.sin(theta) + height + 0.5;
            Vector ear1Offset = perpendicularVector1.clone().multiply(horizontalOffset).setY(verticalOffset);
            Vector ear2Offset = perpendicularVector2.clone().multiply(-horizontalOffset).setY(verticalOffset);
            Location particleLocation = location.clone().add(ear1Offset);
            Location mirroredLocation = location.clone().add(ear2Offset);
            wireframe.add(particleLocation);
            wireframe.add(mirroredLocation);
        }
        return wireframe;
    }
}
