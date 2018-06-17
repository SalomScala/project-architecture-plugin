package com.projectarchitecturedescription.plugin.dimensionsatisfier;

import org.gradle.api.Project;

import com.projectarchitecturedescription.plugin.dependencies.ProjectElement;

public abstract class ProjectDimensionSatisfier extends ProjectDependencyDimensionSatisfier {
	
	@Override
	public boolean approves(final ProjectElement dependency) {
		return this.approvesProject(dependency.getProject());
	}
	
	public abstract boolean approvesProject(Project project);
	
}
