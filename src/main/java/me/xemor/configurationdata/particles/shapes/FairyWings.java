package me.xemor.configurationdata.particles.shapes;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.particles.EntityShape;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class FairyWings extends Shape implements EntityShape {
    //Example of the shape produced can be viewed here: https://gyazo.com/abfdc0fc4f020bd9d47809a0ce575633
    //Video also shows a wisp and halo effect

    @JsonPropertyWithDefault
    private double height = 1;
    @JsonPropertyWithDefault
    private double frequency = 0.05;
    @JsonPropertyWithDefault
    private double size = 1;

    @Override
    public List<Location> getWireframe(LivingEntity entity) {
        Location location = entity.getLocation();
        List<Location> wireframe = new ArrayList<>();
        Vector playerDirection = entity.getEyeLocation().getDirection();
        Vector perpendicularVector1 = getPerpendicularVector(playerDirection);
        Vector perpendicularVector2 = getPerpendicularVector(playerDirection);
        for (double theta = 0; theta < Math.PI; theta += frequency) {
            double radius = Math.sin(2*theta); //https://www.desmos.com/calculator/48zb9jbn4n
            double horizontalOffset = radius * Math.cos(theta) * size;
            double verticalOffset = radius * Math.sin(theta) * size + height;
            Vector wing1Offset = perpendicularVector1.clone().multiply(horizontalOffset).setY(verticalOffset);
            Vector wing2Offset = perpendicularVector2.clone().multiply(-horizontalOffset).setY(verticalOffset); //Generates second wing
            Vector parallelOffset = playerDirection.clone().normalize().multiply(0.35).setY(0); //Moves wings back, behind player rather than inside model
            Location particleLocation = location.clone().add(wing1Offset).subtract(parallelOffset);
            Location mirroredLocation = location.clone().add(wing2Offset).subtract(parallelOffset);
            wireframe.add(particleLocation);
            wireframe.add(mirroredLocation);
        }
        return wireframe;
    }
}
