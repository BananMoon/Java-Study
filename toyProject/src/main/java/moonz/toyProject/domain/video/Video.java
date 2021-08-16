package moonz.toyProject.domain.video;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Video {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotBlank
    private String url;

    @NotEmpty
    private String name;
}
