import org.junit.*

class BeforeClassErrorsTests extends GroovyTestCase {

  @BeforeClass
  static void doit() {
    throw new Exception("Bang!!!!!!!!!!!")
  }
  
  @Test void testIt() {
    true
  }
}
