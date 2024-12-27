package railway.model;

import lombok.*;
import railway.types.Station;
import railway.types.Status;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FilterTicket {
    private Station departStation;
    private Station arriveStation;
    private String departDate;
    private Status status;
}
