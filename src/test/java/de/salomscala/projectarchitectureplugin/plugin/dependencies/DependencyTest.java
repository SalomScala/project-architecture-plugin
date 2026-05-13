package de.salomscala.projectarchitectureplugin.plugin.dependencies;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DependencyTest {

    @Test
    public void testEqualsAndHashCode() {
        Project root = ProjectBuilder.builder().build();
        Project p1 = ProjectBuilder.builder().withParent(root).withName("p1").build();
        Project p2 = ProjectBuilder.builder().withParent(root).withName("p2").build();
        
        ProjectElement e1 = new ProjectElement(p1);
        ProjectElement e2 = new ProjectElement(p2);
        
        Dependency d1 = new Dependency(e1, e2);
        Dependency d2 = new Dependency(e1, e2);
        Dependency d3 = new Dependency(e2, e1);
        
        assertTrue(d1.equals(d2), "d1 should equal d2");
        assertFalse(d1.equals(d3), "d1 should not equal d3");
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    public void testGetters() {
        Project p1 = ProjectBuilder.builder().withName("p1").build();
        Project p2 = ProjectBuilder.builder().withName("p2").build();
        ProjectElement e1 = new ProjectElement(p1);
        ProjectElement e2 = new ProjectElement(p2);
        
        Dependency d = new Dependency(e1, e2);
        assertEquals(e1, d.getFrom());
        assertEquals(e2, d.getTo());
    }
}
