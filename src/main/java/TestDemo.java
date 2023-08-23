import java.util.Random;

public class TestDemo {
	
	public int addPositive(int a, int b) {
		if (a > 0 && b > 0 ) {
			int positiveAnswer = a + b;
			return positiveAnswer;
		} else {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
	}
	
	// This code will multiply the two input integers only if one of the values is ten.
	public int multiplyByTen(int a, int b) {
		// Checks if one value equals ten.
		if (a == 10 || b == 10) {
			// Multiplies the values together if the check was successful.
			int tenAnswer = a * b;
			return tenAnswer;
		} else {
			// Throws the exception if neither value is ten.
			throw new IllegalArgumentException("One value must be ten (10)!");
		}
	}
	
	int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) + 1;
	}
	
	public int randomNumberSquared() {
		int random = getRandomInt();
		int squared = random * random;
		return squared;
	}
}
