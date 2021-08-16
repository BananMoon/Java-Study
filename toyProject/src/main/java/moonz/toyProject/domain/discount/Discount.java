package moonz.toyProject.domain.discount;

import moonz.toyProject.domain.lecture.Lecture;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Discount {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    private Lecture lecture;

    private Integer discount_percent;

    private LocalDateTime discount_period;
}
