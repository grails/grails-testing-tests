import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

class BootStrap {

	def init = { servletContext ->

		// inspired by http://jira.codehaus.org/browse/GRAILS-4609
		assert servletContext != null
				
		def applicationContext = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		assert applicationContext != null
		
		applicationContext.getBean("bootstrapFlagService").setAtBootstrap = true
		
		
		
		// testing http://jira.codehaus.org/browse/GRAILS-6847
		assert servletContext.getResourceAsStream("/css/main.css") != null
    }

	def destroy = {}
}
