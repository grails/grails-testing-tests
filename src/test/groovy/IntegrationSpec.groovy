import spock.lang.*

import static spock.util.matcher.HamcrestSupport.that

class IntegrationSpec extends BaseSpec {
    
    def "integration tests are ok when running with all phases"() {
        when:
        execute('integration', 'test-app') 
        then:
        getOutput() isSuccessfulTestRun()
    }

    def "integration tests are ok when running only integration phase"() {
        when:
        execute('integration', 'test-app', 'integration:') 
        then:
        getOutput() isSuccessfulTestRun()
    }
   
}