package org.demo.converter;

import org.junit.Test;
import org.demo.model.PhoneNumberModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.junit.Assert.assertEquals;

/**
 * Created by hanhu on 16/6/30.
 */
public class StringToPhoneNumberConvertTest {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testConvert() throws Exception {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToPhoneNumberConvert());
        String phoneNumberStr = "010-12345678";
        PhoneNumberModel phoneNumberModel = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);
        System.out.println(phoneNumberModel.toString());
        assertEquals("010", phoneNumberModel.getAreaCode());
        assertEquals("12345678",phoneNumberModel.getPhoneNumber());
    }
}