
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

public class AesGcm256 {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    // Pre-configured Encryption Parameters
    public static int NonceBitSize = 128;
    public static int MacBitSize = 128;
    public static int KeyBitSize = 256;

    private AesGcm256() {
    }

    public static byte[] NewKey() {
        byte[] key = new byte[KeyBitSize / 8];
        SECURE_RANDOM.nextBytes(key);
        return key;
    }

    public static byte[] NewIv() {
        byte[] iv = new byte[NonceBitSize / 8];
        SECURE_RANDOM.nextBytes(iv);
        return iv;
    }


    public static byte[] HexToByte(String hexStr) {
        int len = hexStr.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
                    + Character.digit(hexStr.charAt(i + 1), 16));
        }
        return data;
    }


    public static String toHex(byte[] data) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(Integer.toString(b, 16));
        }
        return builder.toString();
    }



    public static String encrypt(String PlainText, byte[] key, byte[] iv) {
        String sR = "";
        try {
            byte[] plainBytes = PlainText.getBytes("UTF-8");

            GCMBlockCipher cipher = new GCMBlockCipher(new AESFastEngine());
            AEADParameters parameters = new AEADParameters(new KeyParameter(key), MacBitSize, iv, null);

            cipher.init(true, parameters);

            byte[] encryptedBytes = new byte[cipher.getOutputSize(plainBytes.length)];
            int retLen = cipher.processBytes(plainBytes, 0, plainBytes.length, encryptedBytes, 0);
            cipher.doFinal(encryptedBytes, retLen);
            sR = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (UnsupportedEncodingException | IllegalArgumentException | IllegalStateException | DataLengthException | InvalidCipherTextException ex) {
            System.out.println(ex.getMessage());
        }

        return sR;
    }


    public static String decrypt(String EncryptedText, byte[] key, byte[] iv) {
        String sR = "";
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(EncryptedText);

            GCMBlockCipher cipher = new GCMBlockCipher(new AESFastEngine());
            AEADParameters parameters = new AEADParameters(new KeyParameter(key), MacBitSize, iv, null);

            cipher.init(false, parameters);
            byte[] plainBytes = new byte[cipher.getOutputSize(encryptedBytes.length)];
            int retLen = cipher.processBytes(encryptedBytes, 0, encryptedBytes.length, plainBytes, 0);
            cipher.doFinal(plainBytes, retLen);

            sR = new String(plainBytes, Charset.forName("UTF-8"));
        } catch (IllegalArgumentException | IllegalStateException | DataLengthException | InvalidCipherTextException ex) {
            System.out.println(ex.getMessage());
        }

        return sR;
    }

}
