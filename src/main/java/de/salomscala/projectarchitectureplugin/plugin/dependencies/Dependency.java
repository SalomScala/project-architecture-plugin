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
