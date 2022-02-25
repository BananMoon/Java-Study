## 클래스 로더
- 로딩 - 링크 - 초기화 순으로 진행된다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/66311276/155637319-fb38f4d4-dcc6-48af-961d-b8cd27c076ec.png" alt="text" width="500" height="400" />
</p>

### 로딩
- 클래스 로더가 .class 파일(바이트 코드)을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들어 **메소드 영역**에 저장한다.
- 메소드 영역에 저장되는 데이터
  - FQCN(Fully Qualified Class Name :패키지 경로)
  - 클래스? 인터페이스? 이늄인지? 
  - 클래스 내 메소드와 변수
- 로딩이 끝나면 클래스 타입의 Class 객체(`Class<moonz>`, `Class<Book>`)를 생성해서 **힙** 영역에 저장

### 클래스로더 종류 3가지
1. Bootstrap :  classPath에 있는 클래스를 가져오고, 네이티브 코드로 작성되었기 때문에 자바에서 참조하여 출력할 수 없다.
2. Extention : 부트 로더를 상속받는다.
3. Application : classPath와 module을 가져와서 로드한다.

- 아래는 실제 ClassLoaders.java의 내부 코드에는 부트 로더, 플랫폼 로더, 앱 로더가 선언되어 있다. 
- 의존성에 추가한 라이브러리나 프로젝트에 추가한 클래스들은 모두 App 클래스로더가 읽게된다.
- 아래에서도 알 수 있듯이 부모에서 자식 순서대로 클래스 로더가 수행된다.
  - 만약 bootstrap 로더가 클래스를 찾아서 로드하고, 그외 찾지못한 클래스를 Extension 클래스로더가, 그다음 App 클래스로더가 수행합니다.
  - 그래도 찾지 못한다면 런타임 시 `ClassNotFoundException`이 발생하면서 애플리케이션 혹은 스레드가 죽는다.
```java
public class ClassLoaders {
  // the built-in class loaders
  private static final BootClassLoader BOOT_LOADER;
  private static final PlatformClassLoader PLATFORM_LOADER;
  private static final AppClassLoader APP_LOADER;

  // Creates the built-in class loaders.
  static {
    // -Xbootclasspath/a or -javaagent with Boot-Class-Path attribute
    String append = VM.getSavedProperty("jdk.boot.class.path.append");
    BOOT_LOADER =
            new BootClassLoader((append != null && !append.isEmpty())
                    ? new URLClassPath(append, true)
                    : null);
    PLATFORM_LOADER = new PlatformClassLoader(BOOT_LOADER);

    // A class path is required when no initial module is specified.
    // In this case the class path defaults to "", meaning the current
    // working directory.  When an initial module is specified, on the
    // contrary, we drop this historic interpretation of the empty
    // string and instead treat it as unspecified.
    String cp = System.getProperty("java.class.path");
    if (cp == null || cp.isEmpty()) {
      String initialModuleName = System.getProperty("jdk.module.main");
      cp = (initialModuleName == null) ? "" : null;
    }
    URLClassPath ucp = new URLClassPath(cp, false);
    APP_LOADER = new AppClassLoader(PLATFORM_LOADER, ucp);
  }
}
```
직접 App 클래스의 클래스로더를 호출해본다.
```java
class App {
    public static void main(String[] args) {
      ClassLoader classLoader = App.class.getClassLoader();
      System.out.println("classLoader = " + classLoader);
      System.out.println(classLoader.getParent());
      System.out.println(classLoader.getParent().getParent());
    }
}
// 출력
// classLoader = jdk.internal.loader.ClassLoaders$AppClassLoader@69663380
// jdk.internal.loader.ClassLoaders$PlatformClassLoader@7d6f77cc
// null
```

### 링크
- Verify - Prepare - Resolve(선택) 순으로 실행된다.
1. 검증 : .class 파일(바이트 코드)이 유효한 지 체크한다. 
2. Preparation : 클래스 변수(**static** 변수)와 기본값에 필요한 **메모리를 준비**한다.
3. Resolve: 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체한다.
  - Optional) 나중에 심볼릭 레퍼런스를 사용할 때 교체하도록 할 수도 있다.
> 심볼릭 레퍼런스?
> 쉽게 말해 아래 App 클래스가 참조하는 Book 이 심볼릭 레퍼런스이다.
> ```java
> class App {
>   Book book = new Book();
> }
> ```
> App 클래스를 로드할 때는 Book 클래스에 대해 논리적으로 참조하는데,
> 이것을 App을 로드하면서 실제 물리적인 힙에 있는 Book 클래스를 가리키도록 할 수도 있고
> 실제로 사용할 때 교체할 수도 있다. (Optional)

### 초기화 (Initialization)
- static 변수의 값들이 이 시점에 모두 할당된다. (static 블록이 있다면 실행된다.)
```java
class App {
  static String name;
  static {
    name = "moonz";     // 초기화 시점에 static 변수에 할당됨.
  }
}
```

## 마치며
JVM 내에서 가장 중요한 것은 바이트 코드이다.
java 파일들을 컴파일러가 컴파일을 해서 바이트 코드를 생성하면
JVM이 순서대로 메모리에 로드하는 등의 과정을 거치는 것이다.
