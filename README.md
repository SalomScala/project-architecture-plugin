# Project Architecture Plugin

[![Build Status](https://travis-ci.org/SalomScala/project-architecture-plugin.svg?branch=master)](https://travis-ci.org/SalomScala/project-architecture-plugin)
[![Coverity Status](https://scan.coverity.com/projects/16036/badge.svg)](https://scan.coverity.com/projects/16036)

A Gradle plugin for defining and verifying architecture rules for project dependencies.

## Overview

This plugin allows you to describe the architecture of a multi-project build by grouping projects into **dimensions**. **Rules** can be defined between these dimensions to either allow or forbid dependencies. The plugin checks the actual transitive dependencies in the build against these rules and fails the build if violations are found.

## Installation

Add the plugin to your `build.gradle` file in the root project:

```groovy
plugins {
    id 'de.salomscala.projectarchitectureplugin' version '0.4'
}
```

## Configuration

Configuration is done via the `projectArchitectureDescription` block.

### Defining Dimensions

Dimensions group projects based on their name or other criteria.

```groovy
projectArchitectureDescription {
    dimensions {
        dimension('API') {
            projectNamePattern '.*-api'
        }
        dimension('Implementation') {
            projectNamePattern '.*-impl'
        }
        dimension('Common') {
            projectNamePattern '.*-common'
        }
    }
}
```

### Defining Rules

Rules determine which dependencies are permissible between dimensions. By default, everything is allowed unless explicitly forbidden. Alternatively, a "whitelist approach" can be followed using `forbidAllDimensions()`.

```groovy
projectArchitectureDescription {
    rules {
        // Forbids dependencies from API to Implementation
        forbidDimension 'API', 'Implementation'
        
        // Explicitly allows dependencies from Implementation to Common
        allowDimensions 'Implementation', 'Common'
        
        // Forbids all dependencies between the named dimensions (isolation)
        forbidAllDimensions 'API', 'Implementation'
    }
}
```

## Available Tasks

The plugin registers the following tasks:

- `applyDimensions`: Assigns projects to the defined dimensions.
- `listDimensions`: Lists all dimensions and their assigned projects.
- `applyRules`: Prepares the architecture rules.
- `listRules`: Lists all defined rules.
- `applyBuildDependencies`: Analyzes the actual transitive dependencies in the project.
- `checkRules`: Performs the check of dependencies against the rules. This task should be used in CI pipelines.

## Full Example

```groovy
plugins {
    id 'de.salomscala.projectarchitectureplugin' version '0.4'
}

projectArchitectureDescription {
    dimensions {
        dimension('API') {
            projectNamePattern '.*-api'
        }
        dimension('Impl') {
            projectNamePattern '.*-impl'
        }
        dimension('Utils') {
            projectNamePattern '.*-utils'
        }
    }
    
    rules {
        // By default, all dimensions should not know each other
        forbidAllDimensions()
        
        // API may use Utils
        allowDimensions 'API', 'Utils'
        
        // Impl may use API and Utils
        allowDimensions 'Impl', 'API'
        allowDimensions 'Impl', 'Utils'
    }
}

// Optional: hook checkRules into the standard check task
check.dependsOn checkRules
```

## License

This project is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
