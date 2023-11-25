package traffic;

import java.util.Scanner;

public class CommandExecutor {

	Scanner scanner = new Scanner(System.in);

	private static CommandExecutor instance;

	private CommandExecutor(){}

	static CommandExecutor getInstance() {
		if (instance == null) {
			instance = new CommandExecutor();
		}
		return instance;
	}

	void execute(int command) {
		switch(command) {
			case 1:
				System.out.print("Input the road name: ");
				Main.queueThread.enqueue(scanner.nextLine());
				break;
			case 2:
				Main.queueThread.dequeue();
				break;
			case 3:
				Main.inMenu = false;
				break;
			case 0:
				System.out.println("Bye!");
				Main.queueThread.running = false;
				System.exit(1);
				break;
			default:
				System.out.println("Incorrect option");
				break;
		}
	}
}
