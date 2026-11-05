package framework.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    
    private Hash() {}
    
    public static String stringToHash(String string, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        String result = null;
        
        MessageDigest md = MessageDigest.getInstance( algorithm );
        
        result = new BigInteger( 1, md.digest( string.getBytes("UTF-8") ) ).toString(16);
        
        return result;
        
    }

}