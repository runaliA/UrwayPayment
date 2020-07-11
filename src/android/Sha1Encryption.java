package cordova.plugin.payment;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Sha1Encryption {
    public byte[] hash(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md =  MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("utf-8"));
        byte[] md5 = md.digest();
        return md5;
    }

    public String SHA384(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-384");
        md.update(text.getBytes("utf-8"));
        byte[] sha384 = md.digest();
        return String.valueOf(Hex.encodeHex(sha384));
    }

    public String SHA256(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes("utf-8"));
        byte[] sha384 = md.digest();
        return String.valueOf(Hex.encodeHex(sha384));
    }
}
