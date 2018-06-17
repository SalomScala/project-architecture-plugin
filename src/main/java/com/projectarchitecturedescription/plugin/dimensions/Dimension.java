package com.projectarchitecturedescription.plugin.dimensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.projectarchitecturedescription.plugin.dependencies.Element;
import com.projectarchitecturedescription.plugin.dimensionsatisfier.DimensionSatisfier;
import com.projectarchitecturedescription.plugin.dimensionsatisfier.ProjectDimensionSatisfier;
import com.projectarchitecturedescription.plugin.dimensionsatisfier.ProjectNamePatternDimensionSatisfier;

public class Dimension {
	
	private final String name;
	
	private final List<DimensionSatisfier> satisfiers = new ArrayList<>();
	private final Set<Element> dependencies = new LinkedHashSet<>();
	
	public Dimension(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder(this.name);
		if (!this.dependencies.isEmpty()) {
			builder.append(" Dependencies:\r\n");
			builder.append("   " + this.dependencies.stream().map(Element::toString).reduce((a, b) -> a + "\r\n   " + b).orElse(""));
		}
		return builder.toString();
	}
	
	public void projectNamePattern(final String srcPattern) {
		this.satisfiers.add(new ProjectNamePatternDimensionSatisfier(srcPattern));
	}
	
	public void projectCondition(final ProjectDimensionSatisfier satisfier) {
		this.satisfiers.add(satisfier);
	}
	
	public void apply(final Set<? extends Element> dependenciesSoFar) {
		dependenciesSoFar.stream().filter((final Element d) -> this.satisfiers.stream().anyMatch((final DimensionSatisfier s) -> s.approves(d)))
		.forEach(this.dependencies::add);
	}
	
	public Collection<Element> getDependencies() {
		return this.dependencies;
	}
}
