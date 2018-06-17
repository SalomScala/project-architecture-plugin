package com.projectarchitecturedescription.plugin.rules;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

public class ListRulesTask extends DefaultTask {
	
	private final Rules rules;
	
	@Inject
	public ListRulesTask(final Rules rules) {
		this.rules = rules;
		
		this.doLast((final Task t) -> {
			t.getLogger().lifecycle(ListRulesTask.this.rules.toString());
		});
	}
}
