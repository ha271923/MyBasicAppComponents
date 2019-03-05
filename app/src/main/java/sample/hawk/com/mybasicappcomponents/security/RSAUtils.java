package sample.hawk.com.mybasicappcomponents.security;

/**
 * Created by ha271 on 2019/3/5.
 */

import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAUtils
{
    private static String TAG = "RSAUtils";
    private static String RSA = "RSA";
    private static String RSA_ECB_OAEPWITHSHA256ANDMGF1PADDING = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static String RSA_ECB_OAEPWITHSHA1ANDMGF1PADDING = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
    private static String RSA_PKCS1PADDING = "RSA/ECB/PKCS1Padding";
    private static String HASH_FOR_SIGNING_VERIFYING = "SHA256withRSA";

    public static byte[] signData(byte[] data, PrivateKey privateKey) {
        try {
            Signature sig = Signature.getInstance(HASH_FOR_SIGNING_VERIFYING);
            sig.initSign(privateKey);
            sig.update(data);

            return sig.sign();

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {
        try {
            Signature publicSignature = Signature.getInstance(HASH_FOR_SIGNING_VERIFYING);
            publicSignature.initVerify(publicKey);
            publicSignature.update(data);

            return publicSignature.verify(signature);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] encryptData(byte[] data, PublicKey publicKey)
    {
        byte[] ret = null;
        try
        {
            /*
            OAEPParameterSpec sp = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT);
            Cipher cipher = Cipher.getInstance(RSA_ECB_OAEPWITHSHA256ANDMGF1PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, sp);
            */
            Cipher cipher = Cipher.getInstance(RSA_ECB_OAEPWITHSHA1ANDMGF1PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            ret = cipher.doFinal(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)
    {
        byte[] ret = null;
        try
        {
            /*
            OAEPParameterSpec sp = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT);
            Cipher cipher = Cipher.getInstance(RSA_ECB_OAEPWITHSHA256ANDMGF1PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey, sp);
            */

            Cipher cipher = Cipher.getInstance(RSA_ECB_OAEPWITHSHA1ANDMGF1PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            ret =  cipher.doFinal(encryptedData);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public static byte[] decryptData1(byte[] encryptedData, PrivateKey privateKey)
    {
        byte[] ret = null;
        try
        {
            /*
            OAEPParameterSpec sp = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT);
            Cipher cipher = Cipher.getInstance(RSA_ECB_OAEPWITHSHA256ANDMGF1PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey, sp);
            */

            Cipher cipher = Cipher.getInstance(RSA_PKCS1PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            ret =  cipher.doFinal(encryptedData);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    /*
    public static PublicKey getPublicKey(byte[] keyBytes)
    {
        PublicKey publicKey = null;
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            publicKey = keyFactory.generatePublic(keySpec);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(byte[] keyBytes)
    {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            privateKey = keyFactory.generatePrivate(keySpec);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return privateKey;
    }
    */

    private static PublicKey loadPublicKey(String publicKeyStr)
    {
        PublicKey retKey = null;
        try
        {
            byte[] buffer = Base64.decode(publicKeyStr, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            retKey = keyFactory.generatePublic(keySpec);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return retKey;
    }

    private static PrivateKey loadPrivateKey(String privateKeyStr)
    {
        PrivateKey retKey = null;
        try
        {
            byte[] buffer = Base64.decode(privateKeyStr, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            retKey = keyFactory.generatePrivate(keySpec);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return retKey;
    }


    /**
     * Load key from an InputStream
     * @param in InputStream of key file
     * @param bIsPublic true means that InputStream represents as a public key
     * @return
     */
    public static Key loadKey(InputStream in, boolean bIsPublic)
    {
        Key retKey = null;
        try
        {
            String keyStr = readKey(in);
            retKey = bIsPublic ? loadPublicKey(keyStr) : loadPrivateKey(keyStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return retKey;
    }


    /**
     * Convert InputStream to Stream
     * @param in
     * @return
     */
    private static String readKey(InputStream in)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            while ((readLine = br.readLine()) != null)
            {
                if (readLine.charAt(0) == '-')
                {
                    continue;
                }
                else
                {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }


    static public int calculateRSAKeyLength(int nLenOfClear)
    {
        int nRet = 0;
        Log.d(TAG, "calculateRSAKeyLength() data length " + nLenOfClear);
        // accumulate padding limit
        nLenOfClear += 11;

        int nRequireLength = (int) Math.ceil(((double)nLenOfClear) / 128);

        nRet = nRequireLength * 1024;
        Log.d(TAG, "calculateRSAKeyLength() result " + nRet);
        return nRet;
    }
}
