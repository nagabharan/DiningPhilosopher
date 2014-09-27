import java.util.Random;

public class Philosopher implements Runnable {

	private int index;
	private int cycles;
	private Random random = new Random();
	private Chopstick left;
	private Chopstick right;

	public Philosopher(int index, int cycles, Chopstick left, Chopstick right) {
		this.index = index;
		this.cycles = cycles;
		this.left = left;
		this.right = right;
	}

	private void print(String message) {
		System.out.println("Philosopher " + index + " " + message);
	}

	public void run() {
		try {
			for (int i = 0; i < cycles; i++) {
				print("is thinking");
				Thread.sleep(random.nextInt(10000));
				print("is hungry");
				if (index % 2 == 1) {
					print("is grabbing left chopstick");
					left.takeChopstick();
					print("is grabbing right chopstick");
					right.takeChopstick();
				} else {
					if (index == 4) {
						print("is grabbing right chopstick");
						right.takeChopstick();
					} else {
						print("is grabbing left chopstick");
						left.takeChopstick();
						print("is grabbing right chopstick");
						right.takeChopstick();
					}
				}

				print("is eating");
				Thread.sleep(random.nextInt(10000));
				print("is putting down chopsticks");
				left.returnChopstick();
				right.returnChopstick();
				print("is finished eating");
			}
		} catch (InterruptedException e) {
			print("is interrupted");
		}
	}
}
