import spock.lang.*

class TargetingSpec extends BaseSpec {
    
    def "run a single test"() {
        when:
        execute('targeting', 'test-app', 'AllPassing') 
    
        then:
        exitStatus == 0
        output.contains("Running 2 unit tests...")
        getOutput() isSuccessfulTestRun()
    }

    @Issue("GRAILS-6462")
    def "run a single test - via full class name"() {
        when:
        execute('targeting', 'test-app', 'AllPassingTests') 
    
        then:
        exitStatus == 0
        output.contains("Running 2 unit tests...")
        getOutput() isSuccessfulTestRun()
    }

    @Issue("GRAILS-6462")
    def "run a single test method - via full class name"() {
        when:
        execute('targeting', 'test-app', 'AllPassingTests.testPassing') 
    
        then:
        exitStatus == 0
        output.contains("Running 1 unit test...")
        getOutput() isSuccessfulTestRun()
    }

    def "run a single test method"() {
        when:
        execute('targeting', 'test-app', 'OnePassingOneFailing.testPassing')
        
        then:
        exitStatus == 0
        output.contains("Running 1 unit test...")
        getOutput() isSuccessfulTestRun()
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

    @Issue("GRAILS-6764")
    void "classes not ending in the suffixes should not be picked"() {
        when:
        execute('targeting', 'test-app', 'unit:unit')
        
        then:
        output.contains("Completed 4 unit tests, 1 failed") // AllPassingTests and OnePassingOneFailingTests have two each, hence 4
    }

    @Issue("GRAILS-6764")
    void "cant target non test classes"() {
        when:
        execute('targeting', 'test-app', 'SomeOtherThing')
        
        then:
        getOutput() isSuccessfulTestRun()
    }
    
}