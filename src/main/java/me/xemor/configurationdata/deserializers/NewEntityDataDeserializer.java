package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.AttributesData;
import me.xemor.configurationdata.ConfigurationData;
import me.xemor.configurationdata.deserializers.text.EntityTypeDeserializer;
import me.xemor.configurationdata.entity.EntityComponentRegistry;
import me.xemor.configurationdata.entity.NewEntityData;
import me.xemor.configurationdata.entity.components.EntityComponent;
import org.bukkit.entity.EntityType;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class NewEntityDataDeserializer extends JsonDeserializer<NewEntityData> {

    private static final EntityTypeDeserializer entityTypeDeserializer = new EntityTypeDeserializer();

    @Override
    public NewEntityData deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        EntityType type = entityTypeDeserializer.parse(node.path("type").asText());

        NewEntityData builder = new NewEntityData()
                .setType(type == null ? EntityType.ZOMBIE : type)
                .setNameTag(node.path("nameTag").asText())
                .shouldDespawn(node.path("shouldDespawn").asBoolean(true))
                .setCustomNameVisible(node.path("customNameVisible").asBoolean(false))
                .setSilent(node.path("silent").asBoolean(false))
                .setVisualFire(node.path("visualFire").asBoolean(false));

        if (node.get("attributes") != null) {
            builder.setAttributes(context.readValue(node.path("attributes").traverse(), AttributesData.class));
        }
        if (node.get("passenger") != null) {
            // hoping this handles null sensibly without causing infinite recursion
            builder.setPassenger(context.readValue(node.path("passenger").traverse(), NewEntityData.class));
        }
        List<? extends Class<? extends EntityComponent>> relevantComponentClasses = EntityComponentRegistry.getEntityComponentDataClasses(type.getEntityClass());

        Stream<EntityComponent> components = relevantComponentClasses
                .stream()
                .map((entityComponentDataClazz) -> {
                    try {
                        return context.readTreeAsValue(node, entityComponentDataClazz);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        if (node.get("extra") != null) {
            ConfigurationData.getLogger().warning("Please move away from using the extra section within entities");
            Stream<EntityComponent> extraComponents = relevantComponentClasses
                    .stream()
                    .map((entityComponentDataClazz) -> {
                        try {
                            return context.readTreeAsValue(node.path("extra"), entityComponentDataClazz);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            components = Stream.concat(components, extraComponents);
        }

        builder.setEntitySpecificAttributes(components.toList());
        return builder;
    }
}
