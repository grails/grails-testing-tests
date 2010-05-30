import spock.lang.*

class TargetingSpec extends BaseSpec {
	
	def "run a single test"() {
		expect:
		execute('targeting', 'test-app', 'AllPassing') == 0
		output.contains("Running 1 unit test...\nRunning test AllPassingTests...PASSED")
	}

}