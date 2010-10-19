import spock.lang.*

class PassingTestsSpec extends BaseSpec {
	void "run all should-be passing tests"() {
		when:
		execute('passing', 'test-app')
		then:
        getOutput() isSuccessfulTestRun()
	}
}