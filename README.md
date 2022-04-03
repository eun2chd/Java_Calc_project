# 미니프로젝트 계산기 #
![ezgif com-gif-maker](https://user-images.githubusercontent.com/92193144/161262493-e0fb831e-205f-4bcd-b35b-918cd6f13de9.gif)
##
# 계산기 만들면서 느낀점 #
- Arraylist 를 이용해서 패널에 버튼을 생성하는 과정을 좀더 확실히 익힐 수 있었다.
- 구현해야할 과정들이 생각보다 많았다.
- 문자열을 활용해서 숫자로 바꾸고 연산하는 과정을 구현하는곳에서 많이 어려웠다.
- 배열과 arrayList 를 사용하면서 더확실하게 이해할 수 있었다.
# 에러 #
- 다음 에러는 숫자버튼 누르지 않고 바로 연산기호를 누르고 = 버튼을 클릭했을시 나타나는 에러이다.
- 무엇때문인지 원인을 계속 알수가 없었는데 연산값이 없는데 다음 코드에서 원인이 생겼다는걸 발견하였다.
![연산기호 에러](https://user-images.githubusercontent.com/92193144/161264903-ff2fffe1-5f12-4086-99d1-daf023d25ca0.png)
### 코드 수정 전 ###
<pre>
	public double Calculate(String inputText) {
		
		SymbolConver(inputText);
		// 사용자가 입력한 숫자 및 연산자들을 가져옴 (문자열)
		
		double pre = 0.0;
		double current = 0;
		String mod = "";
		String error = "0";
		
		for (String s : Num_Str) {
			if(s.contentEquals("+")) {
				mod = "add";
			}else if(s.contentEquals("-")) {
				mod = "sub";
			}else if(s.contentEquals("×")) {
				mod = "mul";
			}else if(s.contentEquals("÷")) {
				mod = "div";
			}else {
				###기호를 누르고 = 버튼을 클릭하게 되면 current 안에 값이 없기때문에 위 에러가 발생
					 current = Double.parseDouble(s); 
				 if(mod == "add") {
						System.out.println(pre);
						System.out.println(current);
						pre += current;
						System.out.println(pre);
					}else if(mod == "sub") {
						pre -= current;
					}else if(mod == "mul") {
						pre *= current;
					}else if(mod == "div") {
							pre /= current;	
					}else {
						pre = current;
					}
					
			}
		
			
		}		
		

			return pre;	
		

	}
</pre>
### 코드 수정 후 ###
<pre>


  	public double Calculate(String inputText) {
		
		SymbolConver(inputText);
		// 사용자가 입력한 숫자 및 연산자들을 가져옴 (문자열)
		
		double pre = 0.0;
		double current = 0;
		String mod = "";
		String error = "0";
		
		for (String s : Num_Str) {
			if(s.contentEquals("+")) {
				mod = "add";
			}else if(s.contentEquals("-")) {
				mod = "sub";
			}else if(s.contentEquals("×")) {
				mod = "mul";
			}else if(s.contentEquals("÷")) {
				mod = "div";
			}else {
				if(!s.isEmpty()) { // 조건식을 추가해서 에러해결
					 current = Double.parseDouble(s);
				}
	
				 if(mod == "add") {
						System.out.println(pre);
						System.out.println(current);
						pre += current;
						System.out.println(pre);
					}else if(mod == "sub") {
						pre -= current;
					}else if(mod == "mul") {
						pre *= current;
					}else if(mod == "div") {
							pre /= current;	
					}else {
						pre = current;
					}
					
			}
		
			
		}		
		

			return pre;	
		

	}
	
</pre>
  


# 개선해야될 사항 #
- 아무값이 없는 상태에서 <- 클릭하게 되면 에러발생 어떻게 처리해야 에러가 발생하지 않을지 좀더 알아봐야할거같다. 해결못함.
- 초기값을 0으로 나오게 한상태에서 사용자가 값을 입력하면 숫자만 바뀌게 하고싶었지만 아직 어떻게 해야할지 감이 안잡힌다.
- 연산기호를 여러개 클릭하지 못하고 하나만 클릭할수 있도록 수정해야한다. 이부분도 아직 어떻게 해야할지 잘 모르겠다. 좀더 다른코드를 보면서 공부해야할거 같다.
- 최대입력숫자를 제한시키면 좋을거 같다.

