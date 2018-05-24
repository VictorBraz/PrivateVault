import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;


public class Encrypt_Decrypt {
    private final byte [] keyByte = "1598635214856953".getBytes();
    private final SecretKeySpec key = new SecretKeySpec(keyByte, "AES");

    public String encrypt(String inputString)throws Exception{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte [] encrypted = cipher.doFinal(inputString.getBytes());
        byte [] encryptedBase = Base64.encodeBase64(encrypted);
        return new String(encryptedBase);
    }

    public String decrypt(String inputString) throws Exception{
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decodeBytes = Base64.decodeBase64(inputString.getBytes());
        byte [] outputBytes = cipher.doFinal(decodeBytes);
        return new String(outputBytes);
    }
}
