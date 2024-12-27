package railway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User_SignUp extends User {
    private String confirmPassword;
    private String passport;

    public User_SignUp(String email, String password, String confirmPassword, String passport) {
        super(email, password);
        this.confirmPassword = confirmPassword;
        this.passport = passport;
    }
    public String getConfirmPassword(){
        return this.confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }
    public String getPassport(){
        return this.passport;
    }
    public void setPassport(String passport){
        this.passport = passport;
    }




//    public User toUser(){
//        return new User(email,password);
//    }
}
