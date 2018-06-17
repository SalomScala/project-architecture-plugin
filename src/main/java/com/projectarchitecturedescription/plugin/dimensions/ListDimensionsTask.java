package com.projectarchitecturedescription.plugin.dimensions;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

public class ListDimensionsTask extends DefaultTask {
	
	private final Dimensions dimensions;
	
	@Inject
	public ListDimensionsTask(final Dimensions dimensions) {
		this.dimensions = dimensions;
		
		this.doLast((final Task t) -> {
			t.getLogger().lifecycle(ListDimensionsTask.this.dimensions.toString());
		});
	}
}
