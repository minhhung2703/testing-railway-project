package railway.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeatType {
    HARD_SEAT("Hard seat"),
    SOFT_SEAT("Soft seat"),
    SOFT_SEAT_AIR_CONDITIONER("Soft seat with air conditioner"),
    HARD_BED("Hard bed"),
    SOFT_BED("Soft bed"),
    SOFT_BED_CONDITIONER("Soft bed with air conditioner");

    private String text;

    public static SeatType fromText(String text){
        for(SeatType seatType : values()){
            if(seatType.getText().equals(text)){
                return seatType;
            }
        }
        return null;
    }
}
