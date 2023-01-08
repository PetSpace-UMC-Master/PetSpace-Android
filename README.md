# PetSpace-Android

## 0. 중요
+ 주석을 꼭 달아줍시다..!(특히 아직 구현 못한 부분이나 수정이 필요한 부분!)

+ 변수명은 글 처럼 길게 짓습니다!

+ 화면 구현 및 기능 구현은 해당 activity/fragment 에서 연결되는 event까지 해줍니다!

+ 깃 커밋 메시지
-feat : 새로운 기능 추가
-fix : 버그 수정
-docs: 문서 수정
-style : 코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우
-refactor : 코드 리팩토링
-test : 테스트 코드, 리팩토링 테스트 코드 추가
-chore : 빌드 업무 수정, 패키지 매니저 수정
-fix : 버그 수정

# 코딩 컨벤션 (코틀린)

## ☺ 1. 소스 파일 이름

Kotlin 파일에 단일 클래스 또는 인터페이스(잠재적으로 관련 최상위 선언 포함)가 포함된 경우 해당 이름은 .kt확장자가 추가된 클래스 이름과 동일해야 합니다. 모든 유형의 클래스 및 인터페이스에 적용됩니다. 파일에 여러 클래스가 포함되어 있거나 최상위 선언만 있는 경우 파일에 포함된 내용을 설명하는 이름을 선택하고 그에 따라 파일 이름을 지정합니다.
ex) ProcessDeclarations.kt

*파일의 이름은 파일의 코드가 수행하는 작업을 설명해야 합니다.*

## ☺ 2. 함수 이름

함수, 속성 및 지역 변수의 이름은 소문자로 시작하고 카멜 케이스를 사용하며 밑줄은 사용하지 않습니다.
ex) var declarationCount = 1
*Camel Case : 첫 단어는 소문자, 두번째 단어부터 대문자로
rocket launch duration => rocketLaunchDuration

동작 또는 변경 가능한 데이터가 있는 개체를 포함하는 최상위 수준 또는 개체 속성의 이름은 카멜 케이스 이름을 사용해야 합니다.

ex) val mutableCollection: MutableSet<String> = HashSet()

object싱글톤 객체에 대한 참조를 보유하는 속성의 이름은 선언 과 동일한 명명 스타일을 사용할 수 있습니다 .

ex) val PersonComparator: Comparator<Person> = /*...*/

## ☺ 3. 클래스 이름

- 클래스에 개념적으로 동일한 두 개의 속성이 있지만 하나는 공용 API의 일부이고 다른 하나는 구현 세부 정보인 경우 개인 속성 이름의 접두사로 밑줄을 사용합니다.

```
class C {
    private val _elementList = mutableListOf<Element>()

    val elementList: List<Element>
         get() = _elementList
}
```

- 클래스의 이름은 일반적으로 클래스가 무엇인지 설명하는 명사 또는 명사구 입니다.
ex) List, PersonReader

- 메서드의 이름은 일반적으로 메서드가 수행하는 작업을 나타내는 동사 또는 동사구 입니다 
ex) readPersons

- 이름은 엔터티의 목적이 무엇인지 명확하게 나타내야 하므로 이름에 의미 없는 단어(Manager, Wrapper)를 사용하지 않는 것이 좋습니다.



## 😙 4. 기타 참고 사항

- 단일 식으로 구성된 본문이 있는 함수의 경우 식 본문을 사용하는 것이 좋습니다.

fun foo(): Int {     // bad
    return 1
}

fun foo() = 1        // good

- 매우 간단한 읽기 전용 속성의 경우 한 줄 형식을 고려하십시오.

val isEmpty: Boolean get() = size == 0


