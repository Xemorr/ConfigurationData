package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(using = SetData.SetDataDeserializer.class)
public class SetData<T> {

    private final Set<T> set = new HashSet<>();

    public boolean inSet(T t) {
        return set.isEmpty() || set.contains(t);
    }

    public Set<T> getSet() {
        return set;
    }

    public void addValue(T value) {
        set.add(value);
    }

    public static class SetDataDeserializer<T> extends JsonDeserializer<SetData<T>> {
        @Override
        public SetData<T> deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            JavaType contextualType = context.getContextualType();
            Class<T> type = (Class<T>) contextualType.containedType(0).getRawClass();

            SetData<T> setData = new SetData<>();
            JsonNode node = jsonParser.readValueAsTree();
            for (JsonNode element : node) {
                T value = jsonParser.getCodec().treeToValue(element, type);
                setData.addValue(value);
            }

            return setData;
        }
    }

}
