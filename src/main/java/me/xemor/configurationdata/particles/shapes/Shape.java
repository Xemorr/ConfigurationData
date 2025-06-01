package me.xemor.configurationdata.particles.shapes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bukkit.util.Vector;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Point.class)
public abstract class Shape {

    public Shape() { }

    public Vector getPerpendicularVector(Vector directionVector) { //Used for shape types that need to depend on player yaw (facing direction).
        double x = ((directionVector.getZ() * 1)) / (directionVector.getX() * -1);
        return new Vector(x, 0, 1).normalize();
    }
}
