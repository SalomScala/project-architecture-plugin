package de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests für die Klasse ProjectNamePatternDimensionSatisfier.
 */
public class ProjectNamePatternDimensionSatisfierTest {

    @Test
    public void testApproves() {
        Project project = ProjectBuilder.builder().withName("test-project").build();
        ProjectElement element = new ProjectElement(project);
        
        ProjectNamePatternDimensionSatisfier satisfier = new ProjectNamePatternDimensionSatisfier("test-.*");
        
        assertTrue(satisfier.approves(element), "Sollte Projekte akzeptieren, die dem Muster entsprechen");
        
        ProjectNamePatternDimensionSatisfier satisfier2 = new ProjectNamePatternDimensionSatisfier("other-.*");
        assertFalse(satisfier2.approves(element), "Sollte Projekte ablehnen, die nicht dem Muster entsprechen");
    }
}
