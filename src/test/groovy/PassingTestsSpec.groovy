import spock.lang.*

class PassingTestsSpec extends BaseSpec {
	void "run all should-be passing tests"() {
		expect:
		execute('passing', 'test-app') == 0
	}
}