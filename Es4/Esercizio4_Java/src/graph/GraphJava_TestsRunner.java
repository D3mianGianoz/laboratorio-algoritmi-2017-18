package graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class GraphJava_TestsRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(GraphTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}