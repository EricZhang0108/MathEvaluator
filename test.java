package cryptocurrency;

import java.util.*;

public class test {

	public static void main(String [ ] args){
		MathEvaluator math = new MathEvaluator();
		Scanner input = new Scanner(System.in);
		System.out.println("Press q to quit");
		boolean go = true;
		while(go){
			System.out.println("Please enter a math expression: ");
			String exp = input.nextLine();
			if(exp.equals("q")){
				go = false;
			}else{
				System.out.println(math.calculate(exp));
			}
		}
	}

}
