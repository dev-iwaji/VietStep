# Project specific ProGuard rules.

# Kotlin
-keep class kotlin.Metadata { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*

-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

# Gsonで使用するModel
-keep class com.iwaji.vietstep.data.model.** {
    *;
}
