import spock.lang.*

class FunctionalSpec extends BaseSpec {
    
    def "basic functional against local server"() {
        when:
        execute('functional', '-DwarDeployed=false', 'test-app', 'functional:', 'the.testIsWarDeployed') 
        then:
        exitStatus == 0
    }

    @Ignore("waiting for GRAILS-6688")
    @Timeout(300)
    def "basic functional against war"() {
        when:
        execute('functional', '-DwarDeployed=true', 'test-app', 'functional:', 'the.testIsWarDeployed', '-war') 
        then:
        exitStatus == 0
    }
    

    def "against different base url"() {
        given:
        def server = new TestHttpServer()
        server.start()
        server.get = { req, res ->
            res.outputStream << "a"
        }
        
        when:
        execute('functional', '-Dp=a', 'test-app', 'functional:', 'the.testProperty', "-baseUrl=$server.baseUrl")
        then:
        exitStatus == 0
        
        cleanup:
        server.stop()
    }
    
    def "war with base url"() {
        when:
        execute('functional', '-DwarDeployed=true', 'test-app', 'functional:', 'the.testIsWarDeployed', '-baseUrl=http://localhost:8080/functional/', '-war') 
        then:
        exitStatus == 0
    }
    
    def "inline with base url"() {
        when:
        execute('functional', '-DwarDeployed=false', 'test-app', 'functional:', 'the.testIsWarDeployed', '-baseUrl=http://localhost:8080/functional/', '-inline') 
        then:
        exitStatus == 0
    }
    
}