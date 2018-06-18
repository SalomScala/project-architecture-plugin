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
package de.salomscala.projectarchitectureplugin.plugin.dependencies;

public class Dependency {
    private final Element from;
    private final Element to;

    public Dependency(final Element from, final Element to) {
        super();
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final Dependency pair = (Dependency) o;

        return !(this.from != null ? !this.from.equals(pair.from) : pair.from != null)
                && !(this.to != null ? !this.to.equals(pair.to) : pair.to != null);
    }

    public Element getFrom() {
        return this.from;
    }

    public Element getTo() {
        return this.to;
    }

    @Override
    public int hashCode() {
        int result = this.from != null ? this.from.hashCode() : 0;
        result = 31 * result + (this.to != null ? this.to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.from.toString() + "--->" + this.to.toString(); //$NON-NLS-1$
    }
}
