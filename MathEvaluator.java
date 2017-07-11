package cryptocurrency;

import java.text.DecimalFormat;

public class MathEvaluator {

	  public double calculate(String expression) {
		  expression = expression.replaceAll("\\s+","");
		  expression.replace('#', '-');
		  DecimalFormat df = new DecimalFormat("#.################");
		  boolean allInt = true;
		  boolean paren = false;
		  int parenLoc = 0;
		  int otherParen = 0;
		  for(int i = 0; i < expression.length(); i++){
			  if(expression.charAt(i) == '(' || expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/'){
				  allInt = false;
			  }
			  if(expression.charAt(i) == '('){
				  paren = true;
				  parenLoc = i;
			  }
			  if(expression.charAt(i) == '-'){
				  if( i == 0){
					  expression = '#' + expression.substring(1, expression.length());
				  }else if(expression.charAt(i-1) == '+' || expression.charAt(i-1) == '-' || expression.charAt(i-1) == '*' || expression.charAt(i-1) == '/' || expression.charAt(i-1) == '('){
					  expression = expression.substring(0, i) + '#' + expression.substring(i+1, expression.length()); 
				  }
			  }
			  if(expression.charAt(i) == '-'){
				  if(i == 0){
					  System.out.println("hi");
				  }else if(expression.charAt(i-1) == '-' || expression.charAt(i-1) == '#'){
					  expression = expression.substring(0, i-1) + expression.substring(i+1, expression.length());
					  i-=2;
				  }
			  }
		  }
		  if(allInt){
			  return Double.parseDouble(expression.replace('#','-'));
		  }
		  if(paren){
			  for(int i = parenLoc; i < expression.length(); i++){
				  if(expression.charAt(i) == ')'){
					  otherParen = i;
					  break;
				  }
			  }
			  String eval = df.format(calculate(expression.substring(parenLoc+1, otherParen)));
			  return calculate(expression.substring(0, parenLoc)+eval+expression.substring(otherParen+1,expression.length()));
		  }
		  boolean operator = true;
		  char type = '+';
		  int operatorLoc = 0;
		  while(operator){
			 System.out.println(expression);
			 operator = false;
			 for(int i = 0; i < expression.length(); i++){
				  if(expression.charAt(i) == '-'){
					  if( i == 0){
						  expression = '#' + expression.substring(1, expression.length());
					  }else if(expression.charAt(i-1) == '+' || expression.charAt(i-1) == '-' || expression.charAt(i-1) == '*' || expression.charAt(i-1) == '/' || expression.charAt(i-1) == '('){
						  expression = expression.substring(0, i) + '#' + expression.substring(i+1, expression.length()); 
					  }
				  }
				 if(expression.charAt(i) == '*'){
					type = '*';
					operatorLoc = i;
					operator = true;
					break;
				}else if(expression.charAt(i) == '/'){
					type = '/';
					operatorLoc = i;
					operator = true;
					break;
				}
			 }
			 int prevPren = 0;
			 if(operator){
				 for(int i = 0; i < operatorLoc; i++){
						if(expression.charAt(i) == '+' || expression.charAt(i) == '-'){
							prevPren = i;
						}
					 }				 
			 }
			 
			 if(!operator){
				 for(int i = 0; i < expression.length(); i++){
					 if(expression.charAt(i) == '+'){
						type = '+';
						operatorLoc = i;
						operator = true;
						break;
					}else if(expression.charAt(i) == '-'){
						type = '-';
						operatorLoc = i;
						operator = true;
						break;
					}
				 }				 
			 }

			 if(!operator){
				 expression = expression.replace('#', '-');
				 return Double.parseDouble(expression);
			 }
			 int nextOp = 0;
			 operator = false;
			 for(int i = operatorLoc+1; i < expression.length(); i++){
				if(expression.charAt(i) == '+'){
					nextOp = i;
					operator = true;
					break;
				}else if(expression.charAt(i) == '-'){
					nextOp = i;
					operator = true;
					break;
				}else if(expression.charAt(i) == '*'){
					nextOp = i;
					operator = true;
					break;
				}else if(expression.charAt(i) == '/'){
					nextOp = i;
					operator = true;
					break;
				}
			 }
			 double n3 = 0;
			 if(prevPren != 0 && operator){
				 double n1 = Double.parseDouble(expression.substring(prevPren+1,operatorLoc).replace('#', '-'));
				 double n2 = Double.parseDouble(expression.substring(operatorLoc+1, nextOp).replace('#', '-'));
				 switch(type){
				 	case '+':
				 		n3 = n1+n2;
				 		break;
				 	case '-':
				 		n3 = n1-n2;
				 		break;
				 	case '*':
				 		n3 = n1*n2;
				 		break;
				 	case '/':
				 		n3 = n1/n2;
				 		break;
				 	default:
				 		break;
				 }
				 expression = expression.substring(0,prevPren+1) + df.format(n3) + expression.substring(nextOp,expression.length());
				 continue;
			 }
			 if(prevPren != 0){
				 double n1 = Double.parseDouble(expression.substring(prevPren+1, operatorLoc).replace('#', '-'));
				 double n2 = Double.parseDouble(expression.substring(operatorLoc+1, expression.length()).replace('#', '-'));
				 switch(type){
				 	case '+':
				 		n3 = n1+n2;
				 		break;
				 	case '-':
				 		n3 = n1-n2;
				 		break;
				 	case '*':
				 		n3 = n1*n2;
				 		break;
				 	case '/':
				 		n3 = n1/n2;
				 		break;
				 	default:
				 		break;
				 }
				 expression = expression.substring(0,prevPren+1) + df.format(n3);
				 operator = true;
				 continue;
			 }
			 if(!operator){
				 double n1 = Double.parseDouble(expression.substring(0,operatorLoc).replace('#', '-'));
				 double n2 = Double.parseDouble(expression.substring(operatorLoc+1, expression.length()).replace('#', '-'));
				 switch(type){
				 	case '+':
				 		n3 = n1+n2;
				 		break;
				 	case '-':
				 		n3 = n1-n2;
				 		break;
				 	case '*':
				 		n3 = n1*n2;
				 		break;
				 	case '/':
				 		n3 = n1/n2;
				 		break;
				 	default:
				 		break;
				 }
				 return n3;
			 }
			 double n1 = Double.parseDouble(expression.substring(0,operatorLoc).replace('#', '-'));
			 double n2 = Double.parseDouble(expression.substring(operatorLoc+1, nextOp).replace('#', '-'));
			 switch(type){
			 	case '+':
			 		n3 = n1+n2;
			 		break;
			 	case '-':
			 		n3 = n1-n2;
			 		break;
			 	case '*':
			 		n3 = n1*n2;
			 		break;
			 	case '/':
			 		n3 = n1/n2;
			 		break;
			 	default:
			 		break;
			 }
			 expression = df.format(n3) + expression.substring(nextOp, expression.length());
		  }
		  
		  return 1;
	  }
	  

	}