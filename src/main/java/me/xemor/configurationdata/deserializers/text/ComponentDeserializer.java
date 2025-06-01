package me.xemor.configurationdata.deserializers.text;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ComponentDeserializer extends TextDeserializer<Component> {
    @Override
    public Component deserialize(String text, JsonParser jsonParser, DeserializationContext deserializationContext) {
        return MiniMessage.miniMessage().deserialize(text);
    }
}
