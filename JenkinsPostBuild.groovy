#!/usr/bin/env groovy
import jenkins.model.*
import groovy.transform.Field
import hudson.model.Job
//import hudson.slaves.EnvironmentVariablesNodeProperty


Jenkins.instance.getAllItems(hudson.model.AbstractProject.class).each {
println(it.fullName)


def jobNamePattern = it.fullName // adjust to folder/job regex as needed
def long daysBack = 30 // adjust to how many days back to report on
def long timeToDays = 24*60*60*1000 // converts msec to days
def long totalMillis = daysBack*timeToDays
def long currentTime = System.currentTimeMillis()
def long pastTime = currentTime - totalMillis


Jenkins.instance.allItems.findAll() {
  it instanceof hudson.model.FreeStyleProject && it.fullName.matches(jobNamePattern)
  }.each { job ->
      builds = job.getBuilds().byTimestamp(pastTime, currentTime)
      
      Allbuilds = job.getBuilds()
      Allbuild = Allbuilds.number
      newbuild = builds.number
  
      println "number of build to keep :: " + newbuild.size()
      println "total number of build :: " + Allbuilds.size()


// max build to keep code

  def numberOfBuildsToKeep = newbuild.size()
  if( it.class.toString() != "class com.cloudbees.hudson.plugins.folder.Folder" && it.class.toString() != "class org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject") {
    println it.name
    builds = it.getBuilds()
    for(int i = numberOfBuildsToKeep; i < builds.size(); i++) {
    builds.get(i)
    //builds.delete()
    println "Deleted build :: " + builds.get(i)
	}
  }
}
return this
}
