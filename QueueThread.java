package traffic;

import java.util.ArrayList;
import java.util.Scanner;

public class QueueThread extends Thread{
	int seconds = 0;

	int end = 0;

	int roads;

	int interval;

	boolean running;

	ArrayList<String> roadQueue = new ArrayList<>();

	public QueueThread(String name, int roads, int interval) {
		super(name);
		this.roads = roads;
		this.interval = interval;
	}

	public void run() {
		running = true;
		while(running) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			seconds = seconds + 1;

			if (!Main.inMenu) {
				Main.clearCMD();
				System.out.printf("! %d. have passed since system startup !%n" +
						"! Number of roads: %d !%n" +
						"! Interval: %d !%n" , seconds, roads, interval);
				roadQueue.forEach(System.out::println);
				System.out.println("! Press \"Enter\" to open menu !");
			}
		}
	}

	void enqueue(String name) {
		if (roadQueue.size() < roads) {
			roadQueue.add(end, name);
			System.out.println(roadQueue.get(end) + " added!");
			end++;
		} else {
			System.out.println("queue is full");
		}
	}

	void dequeue() {
		if (!roadQueue.isEmpty()) {
			System.out.println(roadQueue.remove(0) + "  deleted!");
			end--;
		} else {
			System.out.println("queue is empty");
		}
	}
}
