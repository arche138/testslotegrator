package org.example.pojos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String currency_code;
    private String email;
    private String name;
    private String password_change;
    private String password_repeat;
    private String surname;
    private String username;

    public static User createUserBody
            (String currency_code, String email, String name, String password_change, String password_repeat,
             String surname, String username) {
        return User.builder()
                .currency_code(currency_code)
                .email(email)
                .name(name)
                .password_change(password_change)
                .password_repeat(password_repeat)
                .surname(surname)
                .username(username)
                .build();
    }
}