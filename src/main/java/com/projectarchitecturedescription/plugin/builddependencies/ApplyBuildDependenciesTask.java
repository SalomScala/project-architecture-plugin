package com.projectarchitecturedescription.plugin.builddependencies;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import com.projectarchitecturedescription.plugin.util.Task;
public class ApplyBuildDependenciesTask extends DefaultTask {
	
	private final BuildDependencies buildDependencies;
	
	@Inject
	public ApplyBuildDependenciesTask(final BuildDependencies dimensions) {
		this.buildDependencies = dimensions;
		
		this.doLast((final org.gradle.api.Task t) -> {
			this.buildDependencies.apply(new Task(t).calculateTransitiveDependencies());
		});
	}
}
