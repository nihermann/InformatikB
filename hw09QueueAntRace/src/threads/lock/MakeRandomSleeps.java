package threads.lock;


import util.LockedQueue;

/**
 * Runs a {@link Sleeper} and a {@link RandomGenerator} with the same
 * {@link util.LockedQueue
 *} which has a capacity of {@code MAX_VALUES}.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class MakeRandomSleeps {

	public static final int MAX_VALUES = 4;

	public static void main(String[] args) {

		LockedQueue<Long> randoms = new LockedQueue<Long>(MAX_VALUES);
		Thread a = new RandomGenerator(randoms);
		Thread b = new Sleeper(randoms);

		a.start();
		b.start();

	}

}
