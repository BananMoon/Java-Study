package moonz.toyProject.domain.review;

import moonz.toyProject.domain.lecture.Lecture;
import moonz.toyProject.domain.user.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Review {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotEmpty
    private String content;

    @NotEmpty
    private Long rating;

    //수정필요할듯
    private User student;

    //수정필요할듯
    private Lecture lecture;

    /** TODO : 엔티티 클래스 생성
     * - User : Reivew = 1 : N (단방향 연관관계)
     * - REVIEW에서만 USER를 참조가능하다는 의미
     *
     */
}
