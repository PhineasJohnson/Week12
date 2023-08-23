import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	
	static Stream<Arguments> argumentsForAddPositive() {
		//formatter:off
		return Stream.of(
				arguments(6, 4, 10, false),
				arguments(0, 8, 8, true)
				);
		//formatter:on
	}
	
	// The arguments for multiplyByTen's parameterized test
	static Stream<Arguments> argumentsForMultiplyByTen() {
		//formatter:off
		return Stream.of(
				arguments(2, 10, 20, false),
				arguments(4, 3, 12, true)
				);
		//formatter:on
	}
	
	// Checks that the actual multiplication is correct.
	@Test
	void assertThatTenIsMultipliedCorrectly() {
		assertThat(testDemo.multiplyByTen(10, 6)).isEqualTo(60);
		assertThat(testDemo.multiplyByTen(10, -2)).isEqualTo(-20);
		assertThat(testDemo.multiplyByTen(10, 800)).isEqualTo(8000);
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(6, 4)).isEqualTo(10);
		assertThat(testDemo.addPositive(8, 46)).isEqualTo(54);
		assertThat(testDemo.addPositive(32, 9)).isEqualTo(41);
		assertThat(testDemo.addPositive(2, 3)).isEqualTo(5);
		assertThat(testDemo.addPositive(5, 12)).isEqualTo(17);
		assertThat(testDemo.addPositive(1, 6)).isEqualTo(7);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
	
	@ParameterizedTest
	@MethodSource("TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	// Checks that the exception is properly thrown under the right conditions.
	@MethodSource("TestDemoJUnitTest#argumentsForMultiplyByTen")
	void assertThatTenAndAnotherMultipliedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.multiplyByTen(a, b));
		} else {
			assertThatThrownBy(() -> testDemo.multiplyByTen(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
}
