import org.codehaus.groovy.grails.web.context.ServletContextHolder

class IntegrationTestTests extends GroovyTestCase {

	def bootstrapFlagService
	def grailsApplication
	
	void testBootstrapWasCalled() {
		assert bootstrapFlagService.setAtBootstrap == true
	}
	
	void testServletContextConfiguredCorrectly() {
		def viaHolder = ServletContextHolder.servletContext		
		def viaContext = grailsApplication.mainContext.servletContext

		// These should be the same object
		assert viaHolder.is(viaContext)		
		
		// Context needs to be configured to use the right path as it's root
		assert viaHolder.getResourceAsStream("/css/main.css") != null
	}
		
}