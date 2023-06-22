package de.hdi.bdd.endpointTest;

import com.intuit.karate.junit5.Karate;

public class BookSotreServerEndpointTestRunner {

    @Karate.Test
    Karate testDummyUsers() {
        return Karate.run("BookEndpointTest").relativeTo(getClass());
    }  
}
