package org.demo.converter;
import org.demo.model.PhoneNumberModel;
import org.springframework.core.convert.converter.Converter;
/**
 * Created by hanhu on 16/6/30.
 */
public class PhoneNumberToStringConverter implements Converter<PhoneNumberModel, String> {
    @Override
    public String convert(PhoneNumberModel phoneNumberModel) {
        if(phoneNumberModel == null){
            return "";
        };

        return phoneNumberModel.getAreaCode() +
                "-" + phoneNumberModel.getPhoneNumber();
    }
}
