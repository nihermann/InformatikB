public class Count{
	public static void checkInput(String[] inputs) throws Exception {
		if (inputs.length != 1){
				throw new Exception("1 argument expected, received " + inputs.length);
		}

		int input;
		try {
	        	input = Integer.parseInt(inputs[0]);
		} catch (NumberFormatException  e) {
			throw new Exception("Invalid input. Input has to be a non-negative integer.");
		}

		if (input < 1) {
			throw new Exception("Only positive numbers are allowed");	
		}
	}
	public static void main(String[] args) throws Exception {
		checkInput(args);

		int count_to = Integer.parseInt(args[0]);
		for (int i = 1; i <= count_to; i++){
			System.out.println(i);
		}
	}
}
