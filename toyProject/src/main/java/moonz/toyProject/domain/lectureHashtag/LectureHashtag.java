package moonz.toyProject.domain.lectureHashtag;

import moonz.toyProject.domain.hashtag.Hashtag;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

public class LectureHashtag {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    private List lectures;

    private Hashtag hashtag;
}
