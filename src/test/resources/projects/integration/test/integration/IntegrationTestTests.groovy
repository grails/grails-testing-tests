import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.plugins.PluginManagerHolder

class IntegrationTestTests extends GroovyTestCase {

	def bootstrapFlagService
	def grailsApplication
	
	def pluginManager
	
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
	
	void testPluginManagerBeanIsTheSameAsInHolder() {
		assert pluginManager.is(PluginManagerHolder.pluginManager)
	}
}