package com.projectarchitecturedescription.plugin.dependencies;

import org.gradle.api.Project;

public class ProjectElement implements Element {
	
	
	public ProjectElement(final Project project) {
		super();
		this.project = project;
	}
	
	private final Project project;
	
	public Project getProject() {
		return this.project;
	}
	
	@Override
	public int hashCode() {
		return this.project.hashCode();
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof ProjectElement) {
			return this.project.equals(((ProjectElement) obj).project);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.project.getDisplayName();
	}
}
