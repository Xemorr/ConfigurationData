package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@JsonAnySetter
@JacksonAnnotationsInside
public @interface JsonAnySetterWithTypedKey {
    /**
     * The type of the key
     * @return The key type
     */
    Class<?> value();
}
