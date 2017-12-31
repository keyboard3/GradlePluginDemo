package  com.keyboard3.pluginproject

import org.gradle.api.Plugin
import org.gradle.api.Project

public class FirstPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("========================");
        System.out.println("这是pluginproject插件!");
        System.out.println("========================");
    }
}