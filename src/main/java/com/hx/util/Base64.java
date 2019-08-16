package com.hx.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by huangch on 2019/8/12 20:53
 * description:
 *
 * @since JDK 1.6
 */
public class Base64 {

    private static Logger logger = LoggerFactory.getLogger(Base64.class);


    public static String encode(String data) {
        try {
            return safeUrlBase64Encode(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("url base64 encode error", e);
            return StringUtils.EMPTY;
        }
    }


    public static String decode(String data) {
        if (StringUtils.isBlank(data)) {
            return StringUtils.EMPTY;
        }
        try {
            return new String(safeUrlBase64Decode(data), "UTF-8");
        } catch (IOException e) {
            logger.error("url base64 decode error", e);
            return StringUtils.EMPTY;
        }
    }


    public static String safeUrlBase64Encode(byte[] data) {
        String encodeBase64 = new BASE64Encoder().encode(data);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", "");
        return safeBase64Str;
    }


    public static byte[] safeUrlBase64Decode(final String safeBase64Str) throws IOException {
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        int mod4 = base64Str.length() % 4;
        if (mod4 > 0) {
            base64Str = base64Str + "====".substring(mod4);
        }
        return new BASE64Decoder().decodeBuffer(base64Str);
    }
}
