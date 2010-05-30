import spock.lang.*

class TargetingSpec extends BaseSpec {
    
    def "run a single test"() {
        when:
        execute('targeting', 'test-app', 'AllPassing') 
    
        then:
        exitStatus == 0
        output.contains("Running 2 unit tests...\nRunning test AllPassingTests...PASSED")
    }

    def "run a single test method"() {
        when:
        execute('targeting', 'test-app', 'OnePassingOneFailing.testPassing')
        
        then:
        exitStatus == 0
        output.contains("Running 1 unit test...\nRunning test OnePassingOneFailingTests...PASSED")
    }

    def "run a single method of two different tests"() {
        when:
        // AllPassing.testFailing, but has the same method name as the method in OnePassingOneFailing
        // So we are testing here that method matching is restricted to the given class
        execute('targeting', 'test-app', 'OnePassingOneFailing.testPassing', 'AllPassing.testFailing')
        
        then:
        exitStatus == 0
        output.contains("Running 2 unit tests...")
    }

}