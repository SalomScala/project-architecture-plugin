package com.projectarchitecturedescription.plugin.dimensions;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import com.projectarchitecturedescription.plugin.ProjectArchitectureDescriptionExtension;
import com.projectarchitecturedescription.plugin.util.Task;
public class ApplyDimensionsTask extends DefaultTask {

	private final Dimensions dimensions;

	@Inject
	public ApplyDimensionsTask() {
	    final ProjectArchitectureDescriptionExtension extension =
            this.getProject().getExtensions().getByType(ProjectArchitectureDescriptionExtension.class);
        this.dimensions = extension.getDimensions();

		this.doLast((final org.gradle.api.Task t) -> {
			this.dimensions.apply(new Task(t).getProjectElements());
		});
	}
}
