package  com.keyboard3.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project

public class SecondPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("========================");
        System.out.println("这是第1个插件!");
        System.out.println("========================");
    }
}