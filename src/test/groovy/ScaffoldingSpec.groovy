import spock.lang.*

class ScaffoldingSpec extends BaseSpec {
	void "failing before class methods should be handled"() {
		when:
		execute('scaffolding', 'test-app', ':unit')
		then:
        exitStatus == 1
        output.contains("Completed 8 unit tests, 3 failed")
	}
}