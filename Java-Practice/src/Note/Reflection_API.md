### 리플렉션 API : 나만의 DI 프레임워크 만들기
> 작성 이유<br>
> 이번 강의에서는 리플렉션 기능을 사용해서 간단한 프레임워크의 DI 기능을 구현해보았습니다.

먼저 해당 프로젝트에서 `ContainerService` 클래스 내에 `getObject()` 메서드를 생성하여 리플렉션 기능 중 
BookRepository라는 클래스를 가져와서 해당 클래스의 필드들 중 `@Inject`가 붙은 필드를 가져와서


1. BookRepository와 BookService 클래스를 생성한다. 각각 레파지토리와 클래스 역할을 한다.
2. `@Inject` 어노테이션을 생성한다. (무조건 런타임 시까지 존재해야하므로 Retention을 설정한다.)
3. BookService에서는 BookRepository에 의존하고 있도록 하고, `@Inject`를 붙여준다.
4. `ContainerService` 클래스 내에 **의존성을 주입하는 기능**을 하는 `getObject()` 메서드를 생성한다.
   1. 리플렉션 기능 `getDeclaredField(), getAnnotation(), getConstructor(), newInstance()` 등을 이용한다.
   2. 인스턴스 생성 로직은 `createInstance()` 메서드로 추출하였다.
5. 테스트코드로 스프링 컨테이너를 실행하여 BookRepository와 BookService를 가져와서 null체크를 한다.
6. BookService의 BookRepository 값은 빈값이 아닌 걸로 나온다.

이렇게 의존성 주입 역할을 테스트코드로 확인하는 것에서 그치지 않고 직접 새 프로젝트에서 해당 프레임워크를 사용해본다.

- 우선 우리가 만든 프레임워크를 빌드한다. 
- 빌드하고 나면 build/libs에 jar 파일이 생긴 것을 확인할 수 있다.
```shell
Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
-a----      2022-02-22  오전 12:26          15710 Java-Practice-0.0.1-SNAPSHOT-plain.jar
-a----      2022-02-22  오전 12:26       21749863 Java-Practice-0.0.1-SNAPSHOT.jar
```
- 새 프로젝트를 생성하고 build.gradle의 의존성 추가하는 곳에 해당 프레임워크 정보를 넣고 빌드하면 해당 프로젝트에서 외부 라이브러리에 추가된다.
- 그후에는 해당 프로젝트에서 외부 Java-Practice 라이브러리의 `@Inject`, `getObject()`를 사용할 수 있다.