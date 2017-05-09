package Validator;

import model.User;

import javax.xml.bind.ValidationException;

/**
 * Created by Toshiba on 5/8/2017.
 */
public class UserValidator implements Validator<User> {
    public void validare(User obj) throws ValidationException {

        if(obj.getName().equals(""))
            throw new ValidationException("Nu exista nume!");
        if(obj.getName().startsWith("1") || obj.getName().startsWith("2") || obj.getName().startsWith("3") || obj.getName().startsWith("4") || obj.getName().startsWith("5") || obj.getName().startsWith("6") || obj.getName().startsWith("7") || obj.getName().startsWith("8") || obj.getName().startsWith("9") || obj.getName().startsWith("0"))
            throw new ValidationException("Numele nu poate incepe cu cifra!");
        if(obj.getUsername().equals(""))
            throw new ValidationException("E nevoie de un username!");
        if(obj.getPassword().equals(""))
            throw new ValidationException("Nu exista parola!");
        if(obj.getPassword().length()<5 || obj.getPassword().length()>20)
            throw new ValidationException("Parola trebuie sa contina intre 5 si 20 de caractere!");

    }
}
