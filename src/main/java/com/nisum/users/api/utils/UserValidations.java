package com.nisum.users.api.utils;

import com.nisum.users.api.constants.Messages;
import com.nisum.users.api.entity.Message;
import com.nisum.users.api.entity.User;

public class UserValidations {

    public static Message userData(User user) {
        Message message = new Message("");
        if(!EmailValidator.isValid(user.getEmail())){
            message = new Message(Messages.INVALID_EMAIL);
        }
        if(!PasswordValidator.isValid(user.getPassword())) {
            message = new Message(Messages.PLEASE_VALIDATE_PASSWORD_FORMAT);
        }
        return message;
    }


}
