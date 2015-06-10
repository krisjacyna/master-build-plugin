package com.krisjacyna.masterbuild

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

import nebula.plugin.dependencylock.DependencyLockPlugin

class MasterBuildPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        setupJava(project)
        setupRepos(project)

        project.plugins.apply(DependencyLockPlugin.class)
    }

    def setupRepos(Project project) {
        project.allprojects {
            repositories {
                mavenLocal()
                mavenCentral()
            }
        }

        project.task('showRepos') << {
            println 'Configured repositories:'
            project.repositories.each {
                println "repository: ${it.name} ('${it.url}')"
            }
        }
    }

    def setupJava(Project project) {
        project.plugins.apply(JavaPlugin.class)
        project.sourceCompatibility = 1.8
        project.targetCompatibility = 1.8
    }
}