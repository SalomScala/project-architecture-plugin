package de.salomscala.projectarchitectureplugin.plugin.dimensions;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DimensionTest {

    @Test
    public void testProjectNamePatternSatisfier() {
        Dimension dimension = new Dimension("API");
        dimension.projectNamePattern(".*-api");
        
        Project p1 = ProjectBuilder.builder().withName("my-api").build();
        Project p2 = ProjectBuilder.builder().withName("my-impl").build();
        
        ProjectElement e1 = new ProjectElement(p1);
        ProjectElement e2 = new ProjectElement(p2);
        
        dimension.apply(Collections.singleton(e1));
        assertTrue(dimension.getDependencies().contains(e1));
        
        dimension.apply(Collections.singleton(e2));
        assertEquals(1, dimension.getDependencies().size(), "Sollte nur ein Element enthalten");
    }

    @Test
    public void testProjectConditionSatisfier() {
        Dimension dimension = new Dimension("Special");
        dimension.projectCondition(new de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier.ProjectDimensionSatisfier() {
            @Override
            public boolean approvesProject(Project project) {
                return project.getName().startsWith("special");
            }
        });
        
        Project p1 = ProjectBuilder.builder().withName("special-one").build();
        ProjectElement e1 = new ProjectElement(p1);
        
        dimension.apply(Collections.singleton(e1));
        assertTrue(dimension.getDependencies().contains(e1));
    }
}
