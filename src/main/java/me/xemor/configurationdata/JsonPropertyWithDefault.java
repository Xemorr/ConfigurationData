package me.xemor.configurationdata;


import com.fasterxml.jackson.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@JsonSetter(nulls = Nulls.SKIP, contentNulls = Nulls.SKIP)
@JsonProperty
@JacksonAnnotationsInside
public @interface JsonPropertyWithDefault {
}
