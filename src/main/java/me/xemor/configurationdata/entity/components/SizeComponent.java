package me.xemor.configurationdata.entity.components;

import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;

public class SizeComponent extends EntityComponent {

    @JsonPropertyWithDefault
    private final Integer size = null;

    @Override
    public void apply(Entity entity, NewEntityData builderSoFar) {
        if (size == null) return;
        if (entity instanceof Slime slime) {
            slime.setSize(size);
        }
        else if (entity instanceof Phantom phantom) {
            phantom.setSize(size);
        }
    }
}
