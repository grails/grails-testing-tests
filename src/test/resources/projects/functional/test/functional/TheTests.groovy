class TheTests extends GroovyTestCase {

	def getBaseUrl() {
		System.properties['grails.testing.functional.baseUrl']
	}
	
	def getActionUrl(actionName) {
		getBaseUrl() + "the/$actionName"
	}
	
	void testIsWarDeployed() {
		assert new URL(getActionUrl('warDeployed')).text == System.properties.warDeployed
	}
	
	void testProperty() {
		assert new URL(getBaseUrl()).text == System.properties.p
	}
	
}