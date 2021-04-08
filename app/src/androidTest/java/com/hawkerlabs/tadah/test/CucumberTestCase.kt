package com.hawkerlabs.tadah.test
//https://medium.com/gumtree-dev-team/android-bdd-with-cucumber-and-espresso-the-full-guide-9c20cfcb8535
import cucumber.api.CucumberOptions
@CucumberOptions(features = ["features"],
    glue = ["com.sniper.bdd.cucumber.steps"],
    tags = ["@e2e", "@smoke"])
@SuppressWarnings("unused")
class CucumberTestCase