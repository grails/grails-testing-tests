import spock.lang.*

class ErrorHandlingSpec extends BaseSpec {
	void "failing before class methods should be handled"() {
		when:
		execute('errors', 'test-app', 'BeforeClassErrors')
		then:
		exitStatus == 1
		output.contains("""-------------------------------------------------------
Running 1 unit test...
Running test BeforeClassErrorsTests...
                    null...FAILED""")
        output.contains("""-------------------------------------------------------
Tests passed: -1
Tests failed: 1
-------------------------------------------------------""")
	}
}