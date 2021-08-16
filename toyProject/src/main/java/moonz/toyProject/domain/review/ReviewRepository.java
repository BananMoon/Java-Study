package moonz.toyProject.domain.review;

import moonz.toyProject.domain.lecture.Lecture;
import moonz.toyProject.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //강의페이지에서 리뷰조회
    Page<Lecture> findByLecturename(@NotEmpty String name, Pageable pageable);

    Optional<User> findByUsername(@NotEmpty String name);



}
