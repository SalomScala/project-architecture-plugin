package de.salomscala.projectarchitectureplugin.plugin.dependencies;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectElementTest {

    @Test
    public void testEqualsAndHashCode() {
        Project root = ProjectBuilder.builder().build();
        Project project1 = ProjectBuilder.builder().withParent(root).withName("p1").build();
        Project project3 = ProjectBuilder.builder().withParent(root).withName("p3").build();

        ProjectElement e1 = new ProjectElement(project1);
        ProjectElement e2 = new ProjectElement(project1);
        ProjectElement e3 = new ProjectElement(project3);

        assertTrue(e1.equals(e2), "e1 should equal e2");
        assertFalse(e1.equals(e3), "e1 should not equal e3");
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    public void testToString() {
        Project project = ProjectBuilder.builder().withName("my-project").build();
        ProjectElement element = new ProjectElement(project);
        assertEquals(project.getDisplayName(), element.toString());
    }
}
