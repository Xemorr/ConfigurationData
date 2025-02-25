package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Collections;
import java.util.List;

public record BookData(String title, String author, List<String> pages) {

    private final static LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().build();

    @JsonCreator
    public BookData(String title, String author, List<String> pages) {
        this.title = title != null ? title : "Xemor is cool";
        this.author = author != null ? author : "";
        this.pages = pages != null ? pages : Collections.emptyList();
    }

    public void applyToBookMeta(BookMeta meta) {
        List<String> serializedPages = pages
                .stream()
                .map((s) -> legacySerializer.serialize(MiniMessage.miniMessage().deserialize(s)))
                .toList();
        String serializedTitle = legacySerializer.serialize(MiniMessage.miniMessage().deserialize(title));
        meta.setGeneration(BookMeta.Generation.ORIGINAL);
        meta.setAuthor(author);
        meta.setPages(serializedPages);
        meta.setTitle(serializedTitle);
    }

}
