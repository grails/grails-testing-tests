class AllPassingTests extends GroovyTestCase {

    void testPassing() {
        
    }
    
    void testFailing() {
        // this doesn't fail, but has to be named the same as the
        // failing method in OnePassingOneFailing to test targeting
        // methods on different classes
    }
}