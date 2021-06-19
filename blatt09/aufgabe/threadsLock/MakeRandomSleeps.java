package threadsLock;

import util.QueueLock;

/**
 * Runs a {@link Sleeper} and a {@link RandomGenerator} with the same
 * {@link QueueLock} which has a capacity of {@code MAX_VALUES}.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class MakeRandomSleeps {

	public static final int MAX_VALUES = 4;

	public static void main(String[] args) {

		QueueLock<Long> randoms = new QueueLock<Long>(MAX_VALUES);
		Thread a = new RandomGenerator(randoms);
		Thread b = new Sleeper(randoms);

		a.start();
		b.start();

	}

}
