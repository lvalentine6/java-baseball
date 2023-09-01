package baseball;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
	public static void main(String[] args) {

		int quit = 0;

		while (!(quit == 2)) {

			List<Integer> computer = new ArrayList<>();
			while (computer.size() < 3) {
				int randomNumber = Randoms.pickNumberInRange(1, 9);
				if (!computer.contains(randomNumber)) {
					computer.add(randomNumber);
				}
			}

			System.out.println("숫자 야구 게임을 시작합니다.");

			System.out.println(computer);
			int st_count = 0;

			while (!(st_count == 3)) {

				System.out.print("숫자를 입력해주세요 : ");
				String tmp = Console.readLine();

				int test;
				test = Integer.parseInt(tmp);
				try {

					if (!(0 < test && test < 1000)) {
						System.out.println("Insert wrong number.");
						break;
					}

				} catch (Exception e) {
					throw new IllegalArgumentException("Insert wrong number");
				}

				List<Integer> input = new ArrayList<>();
				String[] in;
				in = tmp.split("");
				for (int i = 0; i < in.length; i++) {
					input.add(Integer.parseInt(in[i]));
				}

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


