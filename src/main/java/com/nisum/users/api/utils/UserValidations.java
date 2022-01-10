package com.nisum.users.api.utils;

import com.nisum.users.api.constants.Messages;
import com.nisum.users.api.entity.User;
import com.nisum.users.api.exception.CustomException;

public class UserValidations {

    public static void userValidation(User user) throws CustomException {
        if(!EmailValidator.isValid(user.getEmail())){
            throw new CustomException(Messages.INVALID_EMAIL);
        }
        if(!PasswordValidator.isValid(user.getPassword())) {
            throw new CustomException(Messages.PLEASE_VALIDATE_PASSWORD_FORMAT);
        }
    }

}
