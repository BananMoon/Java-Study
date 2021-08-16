package moonz.toyProject.domain.lecture;

import moonz.toyProject.domain.category.Category;
import moonz.toyProject.domain.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public interface LectureRepository {
    //카테고리 조회 시
    Page<Lecture> findByFirstCategory(String parent_category, Pageable pageable);
    Page<Lecture> findBySecondCategory(String child_category, Pageable pageable);
    //findByHashTag는 LectureHashTagRepository에 적었음. 맞을까?

    //강의 조회 시, 강의이름 or 강의자 or intro속 글이 한글자라도 있으면 조회
    Page<Lecture> findByNameOrSharerOrIntro(String name, User instructor, String intro, Pageable pageable);

    //한 검색창에서 동시 조회이기 때문에 22번줄 한개로 합침.
    //    Page<Lecture> findBySharer(User instructor, Pageable pageable);
    //    Page<Lecture> findByLecturename(@NotEmpty String name, Pageable pageable);


    // 강의페이지로 들어간 화면도 여기서 관리하나?
}
