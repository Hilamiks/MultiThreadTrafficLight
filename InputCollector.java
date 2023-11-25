package traffic;
import java.util.Scanner;

public class InputCollector {
	static Scanner scanner = new Scanner(System.in);

	private static InputCollector instance;

	private InputCollector(){}

	public static InputCollector getInstance() {
		if (instance == null) {
			instance = new InputCollector();
		}
		return instance;
	}

	public int getRoads() {
		System.out.print("\nInput the number of roads: ");
		int result = 0;
		do {
			try {
				result = Integer.parseInt(scanner.nextLine());
				if (result <= 0) {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.print("Error! Incorrect Input. Try again: ");
			}
		} while (result <= 0);
		return result;
	}

	public int getInterval() {
		System.out.print("Input the interval: ");
		int result = 0;
		do {
			try {
				result = Integer.parseInt(scanner.nextLine());
				if (result <= 0) {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.print("Error! Incorrect Input. Try again: ");
			}
		} while (result <= 0);
		return result;
	}
}
