package moonz.toyProject.domain.lectureHashtag;

import moonz.toyProject.domain.hashtag.Hashtag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureHashTagRepository {
    Page<Hashtag> findByHashTag(@NotNull Long id, Pageable pageable);

    // 여러 해쉬태그로 조회할 경우도 위의 경우로 가능?

}
