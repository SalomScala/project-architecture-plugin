/*******************************************************************************
 * Copyright  2026 Marius Schultchen
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
package de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Element;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;

/**
 * Abstract base class for satisfiers that specifically check ProjectElements.
 */
public abstract class ProjectDependencyDimensionSatisfier implements DimensionSatisfier {
    /**
     * Default constructor.
     */
    protected ProjectDependencyDimensionSatisfier() {
    }

    /**
     * Checks whether a {@link ProjectElement} satisfies the condition.
     * Delegates to the abstract method {@link #approves(ProjectElement)} if the element is a project.
     *
     * @param dependency The element to check.
     * @return {@code true} if it is a project and satisfies the condition.
     */
    @Override
    public boolean approves(final Element dependency) {
        if (dependency instanceof ProjectElement) {
            final ProjectElement projectDependency = (ProjectElement) dependency;
            return this.approves(projectDependency);
        }
        return false;
    }

    /**
     * Abstract method for checking a project.
     *
     * @param dependency The project element to check.
     * @return {@code true} if the project satisfies the condition.
     */
    public abstract boolean approves(final ProjectElement dependency);
}
