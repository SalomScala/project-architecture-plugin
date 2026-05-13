package de.salomscala.projectarchitectureplugin.plugin.rules;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.Dimension;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.Dimensions;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RulesTest {

    private Dimensions dimensions;
    private Rules rules;
    private Project pApi;
    private Project pImpl;
    private ProjectElement eApi;
    private ProjectElement eImpl;

    @BeforeEach
    public void setup() {
        Project root = ProjectBuilder.builder().build();
        dimensions = new Dimensions();
        dimensions.dimension("API", d -> d.projectNamePattern(".*-api"));
        dimensions.dimension("Impl", d -> d.projectNamePattern(".*-impl"));
        
        rules = new Rules(dimensions);
        
        pApi = ProjectBuilder.builder().withParent(root).withName("my-api").build();
        pImpl = ProjectBuilder.builder().withParent(root).withName("my-impl").build();
        eApi = new ProjectElement(pApi);
        eImpl = new ProjectElement(pImpl);
        
        Set<ProjectElement> elements = new HashSet<>();
        elements.add(eApi);
        elements.add(eImpl);
        dimensions.apply(elements);
    }

    @Test
    public void testForbidDimension() {
        rules.forbidDimension("API", "Impl");
        rules.apply();
        
        Dependency forbidden = new Dependency(eApi, eImpl);
        Dependencies deps = new Dependencies(Collections.singleton(forbidden));
        
        assertThrows(ForbiddenDependencyException.class, () -> rules.check(deps));
    }

    @Test
    public void testAllowOverridesForbid() {
        rules.forbidDimension("API", "Impl");
        rules.allowDimensions("API", "Impl");
        rules.apply();
        
        Dependency allowed = new Dependency(eApi, eImpl);
        Dependencies deps = new Dependencies(Collections.singleton(allowed));
        
        assertDoesNotThrow(() -> rules.check(deps));
    }

    @Test
    public void testForbidAll() {
        rules.forbidAllDimensions();
        rules.apply();
        
        Dependency d1 = new Dependency(eApi, eImpl);
        Dependency d2 = new Dependency(eImpl, eApi);
        
        assertThrows(ForbiddenDependencyException.class, () -> rules.check(new Dependencies(Collections.singleton(d1))));
        assertThrows(ForbiddenDependencyException.class, () -> rules.check(new Dependencies(Collections.singleton(d2))));
    }
}
