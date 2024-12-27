package railway.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ChangePassword {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
