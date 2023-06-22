package de.hdi.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;

public class BookStoreApiBddTest {

	 @Test
	    void testParallel() {
	        Results results = Runner.path("classpath:de.hdi.bdd")
	                .outputJunitXml(true)
	                //.outputCucumberJson(true)
	                .parallel(1);
	        assertEquals(0, results.getFailCount(), results.getErrorMessages());
	 }
}
