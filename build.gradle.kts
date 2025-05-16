// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

val extraProperties = extensions.extraProperties
extraProperties["roomVersion"] = "2.2.4"
extraProperties["archLifecycleVersion"] = "2.2.0"