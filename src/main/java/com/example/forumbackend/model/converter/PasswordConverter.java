package com.example.forumbackend.model.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;

@PropertySource("classpath:application.properties")
@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    @Value("${forum.secret.key}")
    private String key;
    private final String initVector = "fNRJDLaHCK30bqbE";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec sKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);

            byte[] encrypted = cipher.doFinal(attribute.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec sKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(dbData));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}