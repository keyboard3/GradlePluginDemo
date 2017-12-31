package  com.keyboard3.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project

public class SecondPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.afterEvaluate {
            if (project.plugins.hasPlugin("com.android.application")) {
                println "project.afterEvaluate"
                def android = project.extensions.getByName("android")
                println android
            }
        }
    }
}