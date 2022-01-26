package com.harsh.kryptic.utils;

import com.harsh.kryptic.exceptions.InternalServerException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.mindrot.jbcrypt.BCrypt;

import java.io.InputStream;
import java.util.*;

public class Utils {
    public static final String THIRD_PARTY_API_URL_CG = "coingecko.baseurl";
    private static final String PROPS_FILE = "application.properties";

    public static final String USERS_TABLE = "users";

    public static Properties getApplicationProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream stream = loader.getResourceAsStream(PROPS_FILE)) {
            props.load(stream);
        } catch (Exception ex) {
            throw new InternalServerException("Failed to load the properties file.");
        }
        return props;
    }

    public static String getCommaSeparatedCoinsFromListOfCoins(List<String> coins) {
        return String.join(",", coins);
    }

    public static JSONObject getJsonObjectFromObject(Object object) {
        if (object == null) {
            return null;
        }
        Object json;
        JSONObject jsonObject = null;

        try {
            json = new JSONTokener(object.toString().replace("=", ":")).nextValue();
        } catch (JSONException ex) {
            throw new InternalServerException("Failed to parse current market prices");
        }

        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }

        return jsonObject;
    }

    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNull(Object object){
        return object == null;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean comparePassword(String plainText, String hash) {
        return BCrypt.checkpw(plainText, hash);
    }
}
