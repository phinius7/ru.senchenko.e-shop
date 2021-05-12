package ru.geekbrains.senchenko;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"},
        features = {"classpath:features"},
        glue = {"ru.geekbrains.senchenko.steps"},
        snippets = SnippetType.CAMELCASE)
public class LaunchTest {

}
