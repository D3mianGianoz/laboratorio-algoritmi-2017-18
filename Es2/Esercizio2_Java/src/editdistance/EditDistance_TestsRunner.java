package editdistance;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 */
public class EditDistance_TestsRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(EditDistanceTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());

	}
}