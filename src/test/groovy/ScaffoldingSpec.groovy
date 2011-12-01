import spock.lang.*

class ScaffoldingSpec extends BaseSpec {
	void 'scaffolded controller test fails before TODOs are accounted for'() {
	    when:
	    execute 'scaffolding', 'generate-controller', 'scaffolding.Item'
	    then:
	    exitStatus == 0
	    
		when:
		execute 'scaffolding', 'test-app', ':unit'
		then:
        exitStatus != 0
        output.contains "Completed 8 unit tests, 5 failed"
	}
}