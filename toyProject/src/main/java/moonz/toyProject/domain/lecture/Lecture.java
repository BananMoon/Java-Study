package moonz.toyProject.domain.lecture;

import lombok.Builder;
import moonz.toyProject.domain.discount.Discount;
import moonz.toyProject.domain.user.Role;
import moonz.toyProject.domain.user.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Lecture {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    private String intro;

    private Integer price;

    private boolean isExclusive;

    private String cover_image;

    //카드형 강의설명
    private String description;

    //강의 난이도: 초~중고급
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String parent_category;

    private String child_category;

    //흠
    private Role instructor = Role.SHARER;

    private List reviews;

    private List videos;

    private List hashtags;

    private Discount discount;
}
