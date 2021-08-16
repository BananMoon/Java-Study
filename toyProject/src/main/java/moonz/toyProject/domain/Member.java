package moonz.toyProject.domain;

//도메인 : Member라는 테이블.의 칼럼 id, name
//레포지토리
public class Member {
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }
    public void setId() {
        this.id = id;
    }

}
