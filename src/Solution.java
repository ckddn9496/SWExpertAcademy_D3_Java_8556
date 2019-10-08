import java.util.Scanner;
import java.util.Stack;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		sc.nextLine();

		for(int test_case = 1; test_case <= T; test_case++) {
			Stack<Boolean> directionStack = new Stack<>();
			String s = sc.nextLine();
			while (s.length() > 0) {
				if (s.startsWith("north")) {
					directionStack.push(false);
					s = s.substring(5);
				} else if (s.startsWith("west")) {
					directionStack.push(true);
					s = s.substring(4);
				}
			}
			
			long molecular;
			if (directionStack.peek() == false) {
				molecular = 0;
			} else { // (directionStack.peek() == true)
				molecular = 90;
			}
			directionStack.pop();
			int denominator = 1;
			
			while (!directionStack.isEmpty()) {
				boolean dir = directionStack.pop();
				if (dir == false)
					molecular = molecular*2 - 90;
				else 
					molecular = molecular*2 + 90;
				denominator = denominator << 1;
			}
			if (denominator > 1) {
				molecular /= 2;
				denominator /= 2;
			}
			if (denominator == 1) {
				System.out.println("#"+test_case+" "+molecular);
			} else {
				System.out.println("#"+test_case+" "+molecular+"/"+denominator);
			}
			
		}
	}
}