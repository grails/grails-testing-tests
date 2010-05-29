import spock.lang.*

import org.junit.Rule
import org.junit.rules.TestName

class PassingTestsSpec extends BaseSpec {
	@Rule testName = new TestName()
	void "run all should-be passing tests"() {
		expect:
		execute('passing', 'test-app')
	}
}