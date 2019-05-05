package com.nytimes.sample.util

import com.google.gson.*
import timber.log.Timber

import java.lang.reflect.Type


class EmptyToNull<T>
private constructor() : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, tType: Type, context: JsonDeserializationContext): T? {
        if (json.isJsonPrimitive) {
            val jsonPrimitive = json.asJsonPrimitive
            if (jsonPrimitive.isString && jsonPrimitive.asString.isEmpty()) {
                return null
            }
        }
        return context.deserialize<T>(json, tType)
    }


}