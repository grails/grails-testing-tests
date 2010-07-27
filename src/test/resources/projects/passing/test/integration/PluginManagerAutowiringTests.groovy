/**
 * Test for GRAILS-6548
 */
class PluginManagerAutowiringTests extends GroovyTestCase {
    
    def pluginManager
    
    void testPluginManagerWasAutowired() {
        assert pluginManager != null
    }
}
