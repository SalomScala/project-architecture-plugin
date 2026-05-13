package de.salomscala.projectarchitectureplugin.plugin;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectArchitectureDescriptionPluginTest {

    @Test
    public void testPluginAppliesExtensionAndTasks() {
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("projectarchitectureplugin");

        assertNotNull(project.getExtensions().findByName("projectArchitectureDescription"));
        
        assertNotNull(project.getTasks().findByName("listDimensions"));
        assertNotNull(project.getTasks().findByName("applyDimensions"));
        assertNotNull(project.getTasks().findByName("listRules"));
        assertNotNull(project.getTasks().findByName("applyRules"));
        assertNotNull(project.getTasks().findByName("applyBuildDependencies"));
        assertNotNull(project.getTasks().findByName("checkRules"));
    }
}
