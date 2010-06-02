import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AnnotationAutowiringTests {
	@Autowired private Object theService;

	public void test1() {
		assertNotNull(theService);
	}

	public void test2() {
		assertNotNull(theService);
	}
}