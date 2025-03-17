package me.xemor.configurationdata.deserializers.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ComponentDeserializer extends TextDeserializer<Component> {
    @Override
    public Component deserialize(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }
}
