package com.example

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


/**
 * Created by deadlock on 24/1/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationSpec extends Specification {
    def 'Application can boot'(){
        expect:true
    }
}