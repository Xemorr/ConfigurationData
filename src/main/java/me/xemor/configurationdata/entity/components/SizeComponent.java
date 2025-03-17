package me.xemor.configurationdata.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xemor.configurationdata.JsonPropertyWithDefault;
import me.xemor.configurationdata.entity.NewEntityData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SizeComponent implements EntityComponent {

    @JsonPropertyWithDefault
    private Integer size = null;

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
