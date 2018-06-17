package com.projectarchitecturedescription.plugin.builddependencies;

import java.util.LinkedHashSet;
import java.util.Set;

import com.projectarchitecturedescription.plugin.dependencies.Dependencies;
import com.projectarchitecturedescription.plugin.dependencies.Dependency;

public class BuildDependencies {
	
	private final Set<Dependency> actualBuildDependencies = new LinkedHashSet<>();
	
	public void apply(final Dependencies dependencies) {
		this.actualBuildDependencies.addAll(dependencies.getDependencies());
	}
	
	public Dependencies getActualBuildDependencies() {
		return new Dependencies(this.actualBuildDependencies);
	}
}
