package traffic;

import java.util.ArrayList;
import java.util.Scanner;

public class QueueThread extends Thread{
	int seconds = 0;

	int secondsFromStart = 0;

	int indexOfOpenRoad = 0;

	String systemMessage = "";

	int end = 0;

	int roads;

	int interval;

	boolean running;

	ArrayList<Road> roadQueue = new ArrayList<>();

	public QueueThread(String name, int roads, int interval) {
		super(name);
		this.roads = roads;
		this.interval = interval;
	}

	public void run() {
		running = true;
		while(running) {
			updateRoads();
			if (!Main.inMenu) {
				Main.clearCMD();
				System.out.printf("! %d. have passed since system startup !%n" +
						"! Number of roads: %d !%n" +
						"! Interval: %d !%n" , seconds, roads, interval);
				printUpdates();
				System.out.println("! Press \"Enter\" to open menu !");
			}

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			seconds++;
			//secondsFromStart = (secondsFromStart + 1) % interval;
			secondsFromStart++;
			if (secondsFromStart % interval == 0 && secondsFromStart > 0 && roadQueue.size() > 1) {
				changeOpenRoad();
			}
		}
	}

	void enqueue(String name) {
		if (roadQueue.size() < roads) {
			if (roadQueue.isEmpty()) {
				roadQueue.add(end, new Road(name, true));
				secondsFromStart = -1;
			} else {
				roadQueue.add(end, new Road(name, false));
			}
			System.out.println(roadQueue.get(end).name + " added!");
			end++;
		} else {
			System.out.println("queue is full");
		}
	}

	void dequeue() {
		if (!roadQueue.isEmpty()) {
			System.out.println(roadQueue.remove(0).name + "  deleted!");
			end--;
		} else {
			System.out.println("queue is empty");
		}
	}

	void updateRoads() {
		systemMessage = "";
		for (Road road : roadQueue) {
			String status;
			if (road.isOpen) {
				status = "\u001B[32m open for " + (interval - secondsFromStart % interval) + "s.\u001B[0m";
			} else {
				int countdown = 0;
				if(roadQueue.indexOf(road) > indexOfOpenRoad) {
					countdown = (interval - secondsFromStart%interval) +
							(interval * (roadQueue.indexOf(road) - indexOfOpenRoad - 1));
				} else {
					countdown = (interval - secondsFromStart%interval) +
							(interval * (roadQueue.indexOf(road) + roadQueue.size() - indexOfOpenRoad - 1));
				}
				status = "\u001B[31m closed for " + countdown + "s.\u001B[0m";
			}
			systemMessage = systemMessage.concat("\nRoad \"" + road.name + "\" will be" + status);
		}
	}

	void changeOpenRoad() {
		if (roadQueue.size() > 1) {
			roadQueue.get(indexOfOpenRoad).isOpen = false;
			indexOfOpenRoad = (indexOfOpenRoad + 1) % roadQueue.size();
			roadQueue.get(indexOfOpenRoad).isOpen = true;
		} else {
			roadQueue.get(0).isOpen = true;
		}
	}

	void printUpdates() {
		System.out.print(systemMessage + "\n");
	}
}
