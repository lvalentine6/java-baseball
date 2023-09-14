package baseball;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
	public static void main(String[] args) {

		int quit = 0;
		final int NUMBER_SIZE = 3;
		final int CORRECT_STRIKE_COUNT = 3;
		final int MIN_NUMBER_RANGE = 1;
		final int MAX_NUMBER_RANGE = 9;

		while (!(quit == 2)) {

			List<Integer> computer = new ArrayList<>();
			while (computer.size() < NUMBER_SIZE) {
				int randomNumber = Randoms.pickNumberInRange(MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
				if (!computer.contains(randomNumber)) {
					computer.add(randomNumber);
				}
			}

			System.out.println("숫자 야구 게임을 시작합니다.");

			int st_count = 0;

			while (!(st_count == CORRECT_STRIKE_COUNT)) {

				System.out.print("숫자를 입력해주세요 : ");
				String insertion = Console.readLine();

				Input in = new Input();
				List<Integer> input = in.input(insertion);

				Test.testLength(input);
				Test.testvalue(input);

				Strike st = new Strike();

				st_count = st.strike(computer, input);
				int ba_count;
				ba_count = st.ball(computer, input);

				Print p = new Print();
				p.print(ba_count, st_count);

				input.clear();

			}

			System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");

			quit = 0;
			while (!(quit == 1 || quit == 2)) {
				System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
				quit = Integer.parseInt(Console.readLine());
			}

		}
	}
}

//입력한 숫자를 List로 변환
class Input {
	List<Integer> input = new ArrayList<>();
	String[] in;

	List<Integer> input(String insertion) {

		in = insertion.split("");
		for (int i = 0; i < in.length; i++) {
			if (!input.contains(Integer.parseInt(in[i]))) {
				input.add(Integer.parseInt(in[i]));
			}
		}
		return input;
	}
}

class Strike {
	int countSt = 0;
	int countBa = 0;

	int strike(List<Integer> listComputer, List<Integer> listInput) {

		Iterator<Integer> s1 = listComputer.iterator();
		for (int i = 0; s1.hasNext(); i++) {
			int value = s1.next();
			if (value == listInput.get(i)) {
				countSt++;
			}
		}
		return countSt;
	}

	// 볼, 스트라이크 갯수를 셈
	int ball(List<Integer> listComputer, List<Integer> listInput) {

		for (int i = 0; i < listInput.size(); i++) {
			int value = listInput.get(i);
			if (listComputer.contains(value)) {
				countBa++;
			}
		}
		return countBa;
	}
}

// 볼, 스트라이크 출력
class Print {
	void print(int ballcount, int strikecount) {
		if (ballcount == 0) {
			System.out.println("낫싱");
		} else if (strikecount == 0) {
			System.out.println(ballcount + "볼");
		} else if (ballcount == strikecount) {
			System.out.println(strikecount + "스트라이크");
		} else {
			System.out.println((ballcount - strikecount) + "볼 " + strikecount + "스트라이크");
		}
	}
}

//입력 예외 테스트
class Test {
	//입력 숫자의 길이 테스트
	static void testLength(List<Integer> input) {
		final int NUMBER_SIZE = 3;

		if (!(input.size() == NUMBER_SIZE)) {
			throw new IllegalArgumentException("3자리의 중복되지 않는 수를 입력해주세요");
		}

	}

	//입력 숫자의 중복값 테스트
	static void testvalue(List<Integer> input) {
		final int MIN_NUMBER_RANGE = 1;
		final int MAX_NUMBER_RANGE = 9;

		for (int i = 0; i < input.size(); i++) {
			if (!(MIN_NUMBER_RANGE <= input.get(i) && input.get(i) <= MAX_NUMBER_RANGE)) {
				throw new IllegalArgumentException("1~9사이의 숫자만 입력해주세요");
			}
		}

	}

}