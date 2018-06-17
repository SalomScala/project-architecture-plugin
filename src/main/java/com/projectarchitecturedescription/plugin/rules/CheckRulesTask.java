package com.projectarchitecturedescription.plugin.rules;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import com.projectarchitecturedescription.plugin.builddependencies.BuildDependencies;

public class CheckRulesTask extends DefaultTask {
	
	private final Rules rules;
	
	@Inject
	public CheckRulesTask(final Rules rules, final BuildDependencies buildDependencies) {
		this.rules = rules;
		
		this.doLast((final org.gradle.api.Task t) -> {
			this.rules.check(buildDependencies.getActualBuildDependencies());
		});
	}
}
