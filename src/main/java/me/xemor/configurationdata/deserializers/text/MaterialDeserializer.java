package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Material;
import org.bukkit.Registry;

public class MaterialDeserializer extends RegistryDeserializer<Material> {

    public MaterialDeserializer() {
        super(Registry.MATERIAL, "AIR");
    }
}
