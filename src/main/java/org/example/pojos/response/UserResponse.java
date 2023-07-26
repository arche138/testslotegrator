package org.example.pojos.response;
import lombok.Data;

import java.util.Comparator;

@Data
public class UserResponse{
    private String id;
    private String currency_code;
    private String email;
    private String name;
    private String surname;
    private String username;

    public static Comparator<UserResponse> userResponseComparator = new Comparator<>() {

        @Override
        public int compare(UserResponse o1, UserResponse o2) {
            String firstName = o1.getName();
            if (firstName == null) firstName = "";
            String secondName = o2.getName();
            if (secondName == null) secondName = "";
            return firstName.compareTo(secondName);
        }
    };

}
