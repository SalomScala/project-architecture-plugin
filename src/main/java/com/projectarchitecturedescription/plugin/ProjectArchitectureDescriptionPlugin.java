/*******************************************************************************
 * Copyright  2018 Mairus Schultchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.projectarchitecturedescription.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import com.projectarchitecturedescription.plugin.builddependencies.ApplyBuildDependenciesTask;
import com.projectarchitecturedescription.plugin.dimensions.ApplyDimensionsTask;
import com.projectarchitecturedescription.plugin.dimensions.ListDimensionsTask;
import com.projectarchitecturedescription.plugin.rules.ApplyRulesTask;
import com.projectarchitecturedescription.plugin.rules.CheckRulesTask;
import com.projectarchitecturedescription.plugin.rules.ListRulesTask;

public class ProjectArchitectureDescriptionPlugin implements Plugin<Project> {
	private static final String EXTENSION_NAME = "projectArchitectureDescription";
	
	@Override
	public void apply(final Project project) {
		final ProjectArchitectureDescriptionExtension extension =
				project.getExtensions().create(EXTENSION_NAME, ProjectArchitectureDescriptionExtension.class, project.getObjects());
		
		final ListDimensionsTask listDimensionsTask = project.getTasks().create("listDimensions", ListDimensionsTask.class, extension.getDimensions());
		final ApplyDimensionsTask applyDimensionsTask = project.getTasks().create("applyDimensions", ApplyDimensionsTask.class, extension.getDimensions());
		listDimensionsTask.mustRunAfter(applyDimensionsTask);
		final ListRulesTask listRulesTask = project.getTasks().create("listRules", ListRulesTask.class, extension.getRules());
		final ApplyRulesTask applyRulesTask = project.getTasks().create("applyRules", ApplyRulesTask.class, extension.getRules());
		listRulesTask.mustRunAfter(applyRulesTask);
		applyRulesTask.dependsOn(applyDimensionsTask);
		final ApplyBuildDependenciesTask applyBuildDependenciesTask =
				project.getTasks().create("applyBuildDependencies", ApplyBuildDependenciesTask.class, extension.getBuildDependencies());
		final CheckRulesTask checkRulesTask = project.getTasks().create("checkRules", CheckRulesTask.class, extension.getRules(), extension.getBuildDependencies());
		checkRulesTask.dependsOn(applyRulesTask);
		checkRulesTask.dependsOn(applyBuildDependenciesTask);
	}
}