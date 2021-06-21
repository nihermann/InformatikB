package antRace;


public class AntRace implements AntFields {

	public static void main(String[] args) {

		AntField field = new AntField(FIELD4);

		Ant ant = new Ant(field, 2, 4, 1);

		ThreadGroup t = new ThreadGroup("Ants");

		new Thread(t, ant).start();

		waitForGroupToFinish(t);

		System.out.println(field);

		//Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println(field)));
	}

	/**
	 * Waits for all threads of a certain {@link ThreadGroup} {@code t} to finish.
	 * @param t the {@link ThreadGroup} to be waited for.
	 */
	private static void waitForGroupToFinish(ThreadGroup t) {
		synchronized(t){
			while(t.activeCount() > 0){
				try {
					t.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
