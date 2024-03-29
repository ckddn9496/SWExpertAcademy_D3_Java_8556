# SWExpertAcademy_D3_Java_8556

## SW Expert Academy D3 8556. 북북서

### 1. 문제설명

출처: https://swexpertacademy.com/main/code/problem/problemList.do

키보드로 입력을 받아 북쪽과 서쪽 사이의 방향을 자세히 나타내는 문제.

* `north(북)`은 `0`도를 나타낸다.

* `west(서)`는 `90`도를 나타낸다.

* 어떤 방향 `dir`이 `a`도를 나타내고, `dir`서 이전에 등장한 `north`와 `west`의 총 횟수가 `n`회일 때,
`north` `dir` 방향은 `a-(90/2n)`도를, `west` `dir`방향은 `a+(90/2n)`도를 나타낸다.

[입력]
> 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
> 각 테스트 케이스의 첫 번째 줄에는 하나의 문자열이 주어진다.
> 이 문자열에서 “north”와 “west”가 등장한 횟수의 합은 1회 이상 20회 이하이고,
> 위에서 정의한 대로 0도 이상 90도 이하의 각도를 구할 수 있도록 주어진다.


[출력]
> 각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,
> 각 테스트 케이스마다 주어진 방향이 몇 도인지 정수로 나타나면 정수로,
> 아니라면 기약분수의 형태로 출력한다. 정확한 방식은 출력 예제를 참고하라.

### 2. 풀이

`sc.nextLine()`을 이용하여 테스트 케이스를 읽어온다. `startWith()`함수를 이용하여 `north`와 `west`값을 읽고, 역순으로 각도를 계산하기위해 `Stack`을 이용하여 저장하였다. 위 문제는 input에 따라 output에 패턴이있었다. 방향의 갯수가 `2`개 이하일 때 output은 정수이며 그 이상일때 `(분자)/(분모)`의 형태를 띈다. `분모`가 `1`인 경우에 `분자`만 출력하면 되므로 `분자`와 `분모`를 담는 변수를 선언하여 `Stack`에서 방향을 `pop()`하여 그에 맞는 연산을 `분자`에 적용하고 `분모`를 두배로 해주었다. `Stack.isEmpty()`가 되면 반복을 종료하고 기약분수로 나누어 떨어지도록 `분자`와 `분모`를 조정한다. 마지막으로, `분모`의 값이 `1`이면 테스트케이스의 번호와 `분자`만 출력하고 그렇지 않다면 `분자/분모`형태로 출력하여 완성하였다.

```java
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
```

### 3. 이상한점...

프로그래머스에서만 문제를 풀다가 SW Expert Academy에서 오랜만에 문제를 풀어보았는데 컴파일 후 TEST를 Run하였을 때 `Stack Overflow`가 났다. 스택에 저장하는 자료형을 `Integer`대신 `Boolean`으로 바꾸고 곱하기`2`연산들을 모두 시프트연산자를 이용하는것으로 수정하였으나 결과는 그대로였다.

더 메모리를 줄일 수 있는 방법을 생각하다가 일단 제출 하고 이어서 해보자하며 제출하기 버튼을 눌렀는데... 성공이다... 코드가 문제가 아닌건가? SW Expert Academy에서 더 문제를 풀어보면서 이 플랫폼에 더 적응을 해야겠다. 
