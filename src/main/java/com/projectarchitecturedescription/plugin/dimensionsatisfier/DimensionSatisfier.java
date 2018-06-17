package com.projectarchitecturedescription.plugin.dimensionsatisfier;

import com.projectarchitecturedescription.plugin.dependencies.Element;

public interface DimensionSatisfier {
	boolean approves(final Element dependency);
}
