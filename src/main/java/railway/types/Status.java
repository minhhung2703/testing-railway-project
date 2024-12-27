package railway.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    IGNORE("Ignore"),
    EXPIRED("Expired"),
    NEW("New"),
    PAID("Paid");

    private String text;

    public static Status fromText(String text){
        for (Status status : values()){
            if(status.getText().equals(text)) {
                return status;
            }
        }
        return null;
    }
}
