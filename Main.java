package traffic;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	static boolean inMenu;

	static QueueThread queueThread;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		//greet user
		welcome();

		//gathers info
		InputCollector collector = InputCollector.getInstance();
		int roads = collector.getRoads();
		int interval = collector.getInterval();

		//starts the program
		inMenu = true;
		queueThread = new QueueThread("QueueThread",roads, interval);
		queueThread.start();

		Menu menu = Menu.getInstance();
		CommandExecutor executor = CommandExecutor.getInstance();

		int command = -1;
		while (command != 0) {
			menu.display();
			command = menu.getCommand();
			executor.execute(command);
			scanner.nextLine();
			inMenu = true;
			clearCMD();
		}
	}

	public static void welcome() {

		System.out.print("Welcome to the traffic management system!");

	}

	public static void clearCMD() {
		try {
			var clearCommand = System.getProperty("os.name").contains("Windows")
					? new ProcessBuilder("cmd", "/c", "cls")
					: new ProcessBuilder("clear");
			clearCommand.inheritIO().start().waitFor();
		} catch (IOException | InterruptedException e) {}
	}
}
