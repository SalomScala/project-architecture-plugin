package com.projectarchitecturedescription.plugin.dimensions;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import com.projectarchitecturedescription.plugin.util.Task;
public class ApplyDimensionsTask extends DefaultTask {
	
	private final Dimensions dimensions;
	
	@Inject
	public ApplyDimensionsTask(final Dimensions dimensions) {
		this.dimensions = dimensions;
		
		this.doLast((final org.gradle.api.Task t) -> {
			this.dimensions.apply(new Task(t).getProjectElements());
		});
	}
}
