package main.java;

//import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;


public class Encrypt_Decrypt {

    private String secretKey = "1598635214856953"; // 128 bit key

        Cipher ecipher;
        Cipher dcipher;
        // 8-byte Salt
        byte[] salt = {
                (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
                (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
        };
        // Iteration count
        int iterationCount = 19;

        public String encrypt (String plainText)
                throws Exception {
            //Key generation for enc and desc
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            //Enc process
            ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            String charSet = "UTF-8";
            byte[] in = plainText.getBytes(charSet);
            byte[] out = ecipher.doFinal(in);
            String encStr = new String(Base64.getEncoder().encode(out));
            return encStr;
        }

        public String decrypt(String encryptedText)
                throws Exception {

            //Key generation for enc and desc
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            //Decryption process; same key will be used for decr
            dcipher = Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            byte[] enc = Base64.getDecoder().decode(encryptedText);
            byte[] utf8 = dcipher.doFinal(enc);
            String charSet = "UTF-8";
            String plainStr = new String(utf8, charSet);
            return plainStr;
        }

}
