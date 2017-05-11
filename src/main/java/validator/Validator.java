package validator;

import javax.xml.bind.ValidationException;

/**
 * Created by Toshiba on 5/8/2017.
 */
public interface Validator<T> {
    void validare(T obj) throws ValidationException;
}
