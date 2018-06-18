package com.projectarchitecturedescription.plugin.dimensions;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

import com.projectarchitecturedescription.plugin.ProjectArchitectureDescriptionExtension;

public class ListDimensionsTask extends DefaultTask {

	private final Dimensions dimensions;

	@Inject
	public ListDimensionsTask() {
	    final ProjectArchitectureDescriptionExtension extension = this.getProject().getExtensions().getByType(ProjectArchitectureDescriptionExtension.class);
		this.dimensions = extension.getDimensions();
		this.doLast((final Task t) -> {
			t.getLogger().lifecycle(ListDimensionsTask.this.dimensions.toString());
		});
	}
}
