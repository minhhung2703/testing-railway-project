package railway.model;

import lombok.*;
import railway.types.SeatType;
import railway.types.Station;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ticket {
    private LocalDate departDate;
    private Station departFrom;
    private Station arriveAt;
    private SeatType seatType;
    private int amount;
}

