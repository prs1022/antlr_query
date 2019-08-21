package com.antlr.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @Author prs
 * @Date 2019/08/21
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:out.html"}, glue = "com.test.cucumber",
        tags = {"@Query"},
        features = "src/test/resources/features/query.feature")
public class CucumberTest {
}
