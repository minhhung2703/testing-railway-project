package railway.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Station {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    HUE("Huế"),
    DA_NANG("Đà Nẵng"),
    QUANG_NGAI("Quảng Ngãi"),
    NHA_TRANG("Nha Trang");

    private String text;

    public static Station fromText(String text){
        for(Station station : values()){
            if(station.getText().equals(text)){
                return station;
            }
        }
        return null;
    }
}
