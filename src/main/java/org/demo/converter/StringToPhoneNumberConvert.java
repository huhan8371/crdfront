package org.demo.converter;
import org.demo.model.PhoneNumberModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by hanhu on 16/6/30.
 */
public class StringToPhoneNumberConvert implements Converter<String, PhoneNumberModel> {
    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

    @Override
    public PhoneNumberModel convert(String s) {
        if (!StringUtils.hasLength(s)) {
            return null;
        }
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            PhoneNumberModel phoneNumberModel = new PhoneNumberModel();
            phoneNumberModel.setAreaCode(matcher.group(1));
            phoneNumberModel.setPhoneNumber(matcher.group(2));
            return phoneNumberModel;
        } else {
            throw new IllegalArgumentException(String.format("StringToPhoneNumberConvert 类型格式转化错误，需要格式[010-12345678],但格式是[%s]", s));

        }
    }
}
