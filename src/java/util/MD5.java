package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author joaosantos
 */
public class MD5 {

    public String criarMD5ParaEnviarLink(String id) throws NoSuchAlgorithmException {

        String hash = id;
        MessageDigest m = null;

        m = MessageDigest.getInstance("MD5");

        m.update(hash.getBytes(), 0, hash.length());
        String saida = new BigInteger(1, m.digest()).toString(16);
        return saida;
    }

    public String criarMD5ParaPrefixoFoto(String id) throws NoSuchAlgorithmException {

        String hash = id;
        MessageDigest m = null;

        m = MessageDigest.getInstance("MD5");

        m.update(hash.getBytes(), 0, hash.length());
        String saida = new BigInteger(1, m.digest()).toString(16);
        return saida;
    }
}
