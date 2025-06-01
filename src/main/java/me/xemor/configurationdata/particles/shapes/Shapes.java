package me.xemor.configurationdata.particles.shapes;

import com.fasterxml.jackson.databind.jsontype.NamedType;

import java.util.Arrays;

public enum Shapes {
    HELIX(Helix.class), WINGS(Wings.class), POINT(Point.class), HALO(Halo.class), WISP(Wisp.class), HEART(Heart.class), FAIRY_WINGS(FairyWings.class), EARS(Ears.class);
    //Current shape types available

    private Class<? extends Shape> shapeClass;

    Shapes(Class<? extends Shape> shapeClass) {
        this.shapeClass = shapeClass;
    }

    public Class<? extends Shape> getShapeClass() {
        return shapeClass;
    }

    public static NamedType[] getNamedSubTypes() {
        return Arrays.stream(Shapes.values()).map((shape) -> new NamedType(shape.shapeClass, shape.name())).toArray(NamedType[]::new);
    }
}
