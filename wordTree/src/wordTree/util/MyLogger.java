
package wordTree.util;

public class MyLogger{

	public static enum DebugLevel {RELEASE, DEBUG, VERBOSE, RUNNABLE, DELETED, CONSTRUCTOR};

	private static DebugLevel debugLevel;


	public static void setDebugValue (int levelIn) {
		switch (levelIn) {
		case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
		case 3: debugLevel = DebugLevel.RUNNABLE; break;
		case 2: debugLevel = DebugLevel.VERBOSE; break;
		case 1: debugLevel = DebugLevel.DEBUG; break;
		case 0: debugLevel = DebugLevel.RELEASE; break;
		}
	}

	public static void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	// @return None
	public static void writeMessage (String  message  ,
			DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}
