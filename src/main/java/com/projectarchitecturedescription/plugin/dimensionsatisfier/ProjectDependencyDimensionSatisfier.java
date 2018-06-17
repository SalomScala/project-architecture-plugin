package com.projectarchitecturedescription.plugin.dimensionsatisfier;

import com.projectarchitecturedescription.plugin.dependencies.Element;
import com.projectarchitecturedescription.plugin.dependencies.ProjectElement;

public abstract class ProjectDependencyDimensionSatisfier implements DimensionSatisfier {
	
	@Override
	public boolean approves(final Element dependency) {
		if (dependency instanceof ProjectElement) {
			final ProjectElement projectDependency = (ProjectElement) dependency;
			return this.approves(projectDependency);
		}
		return false;
	}
	
	public abstract boolean approves(final ProjectElement dependency);
}
