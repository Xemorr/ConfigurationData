package me.xemor.configurationdata.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.xemor.configurationdata.AttributesData;
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
                .setType(type)
                .setNameTag(node.path("nameTag").asText())
                .shouldDespawn(node.path("shouldDespawn").asBoolean(true))
                .setCustomNameVisible(node.path("customNameVisible").asBoolean(false))
                .setSilent(node.path("silent").asBoolean(false))
                .setVisualFire(node.path("visualFire").asBoolean(false))
                // hoping this handles null sensibly without causing infinite recursion
                .setAttributes(context.readValue(node.path("attributes").traverse(), AttributesData.class))
                .setPassenger(context.readValue(node.path("passenger").traverse(), NewEntityData.class));

        List<? extends Class<? extends EntityComponent>> relevantComponentClasses = EntityComponentRegistry.getEntityComponentDataClasses(type.getEntityClass());

        Stream<EntityComponent> components = relevantComponentClasses
                .stream()
                .map((entityComponentDataClazz) -> {
                    try {
                        return context.readValue(node.traverse(), entityComponentDataClazz);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        if (node.get("extra") != null) {
            Stream<EntityComponent> extraComponents = relevantComponentClasses
                    .stream()
                    .map((entityComponentDataClazz) -> {
                        try {
                            return context.readValue(node.traverse(), entityComponentDataClazz);
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
