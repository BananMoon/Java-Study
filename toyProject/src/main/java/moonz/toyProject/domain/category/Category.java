package moonz.toyProject.domain.category;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Category {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    private Category parent;

    private List child;
}
