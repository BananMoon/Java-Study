package moonz.toyProject.domain.hashtag;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Hashtag {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    private String value;
}
