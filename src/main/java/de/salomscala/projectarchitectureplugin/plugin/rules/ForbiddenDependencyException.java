/*******************************************************************************
 * Copyright  2018 Marius Schultchen
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
package de.salomscala.projectarchitectureplugin.plugin.rules;

import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;

public final class ForbiddenDependencyException extends RuntimeException {
    private static final long serialVersionUID = 8003030628963621353L;

    public ForbiddenDependencyException(final Set<Dependency> forbiddenDependencies) {
        super(errorMessag(forbiddenDependencies));
    }

    private static String errorMessag(final Set<Dependency> forbiddenDependencies) {
        final StringBuilder builder = new StringBuilder("The build contains the following forbidden dependencies:\r\n");
        forbiddenDependencies
                .forEach((final Dependency d) -> builder.append("   ").append(d.toString()).append("\r\n"));
        return builder.toString();
    }
}
