import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int length = str.length();
		int minFA = Integer.MAX_VALUE;

		for (int i = 1; i <= Math.min(length / 3, 10); i++) {

			for (int j = i + 1; j <= Math.min(length, i + 11); j++) {

				if (str.charAt(i) == '0')
					continue;

				long a1 = Long.parseLong(str.substring(0, i));
				long a2 = Long.parseLong(str.substring(i, j));
				long d = a2 - a1;

				if (d <= 0)
					continue;

				long prev = a2;
				int index = j;

				while (index < length) {
					if (str.charAt(j) == '0')
						break;

					long next = prev + d;
					String nextStr = String.valueOf(next);
					int nextLen = nextStr.length();

					if (str.charAt(index) != '0' && length - index <= 10
							&& Long.parseLong(str.substring(index)) < 1000000000
							&& Long.parseLong(str.substring(index)) % prev == 0
							&& Long.parseLong(str.substring(index)) != prev) {
						minFA = Math.min(minFA, Integer.parseInt(str.substring(index)) / (int) prev);
					}
					if (str.charAt(index) != '0' && index + nextLen <= length
							&& str.substring(index, index + nextLen).equals(nextStr)) {
						prev = next;
						index += nextLen;

					} else {
						break;
					}
				}
			}
		}

		System.out.println(minFA == Integer.MAX_VALUE ? 0 : minFA);
	}
}
