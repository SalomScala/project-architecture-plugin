package com.projectarchitecturedescription.plugin.rules;

import com.projectarchitecturedescription.plugin.dependencies.Dependencies;

public interface Rule {
	public abstract void apply();
	
	public abstract Dependencies getForbiddenDependencies();
	
	public abstract Dependencies getAllowedDependencies();
}
