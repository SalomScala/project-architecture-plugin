package com.projectarchitecturedescription.plugin.rules;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

public class ApplyRulesTask extends DefaultTask {
	
	private final Rules rules;
	
	@Inject
	public ApplyRulesTask(final Rules rules) {
		this.rules = rules;
		
		this.doLast((final Task t) -> {
			this.rules.apply();
		});
	}
}
