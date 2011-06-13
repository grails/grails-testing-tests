import spock.lang.*

class ErrorHandlingSpec extends BaseSpec {
	void "failing before class methods should be handled"() {
		when:
		execute('errors', 'test-app', 'BeforeClassErrors')
		then:
		exitStatus == 1
		getOutput() isFailedTestRun()
	}
}