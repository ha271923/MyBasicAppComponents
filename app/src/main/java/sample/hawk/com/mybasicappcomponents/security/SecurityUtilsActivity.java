package sample.hawk.com.mybasicappcomponents.security;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import sample.hawk.com.mybasicappcomponents.R;

import static java.io.File.separator;

/**
 * Created by ha271 on 2019/3/5.
 */

public class SecurityUtilsActivity extends Activity {

    public static final String TAG = "SecurityUtilsActivity";
    private static String testStr = "A plaintext is the input text for testing.";
    Context mAppContext;
    TextView tvInputText1;
    CheckBox cbEncrypt;
    TextView tvOutputText1;
    TextView tvInputText2;
    CheckBox cbSign;
    TextView tvOutputText2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securityutils_activity);
        mAppContext = this.getApplicationContext();
        initViews();
        tvInputText1.setText(testStr);
        tvInputText2.setText(testStr);
    }

    void initViews(){
        tvInputText1 = (TextView) findViewById(R.id.tvInputText1);
        cbEncrypt = (CheckBox) findViewById(R.id.cbEncrypt);
        cbEncrypt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    if(isChecked) {
                        RSA_tests(TEST.ENCRYPT);
                    } else {
                        RSA_tests(TEST.DECRYPT);
                    }
              }
          }
        );
        tvOutputText1 = (TextView) findViewById(R.id.tvOutputText1);


        tvInputText2 = (TextView) findViewById(R.id.tvInputText2);
        cbSign = (CheckBox) findViewById(R.id.cbSign);
        cbSign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                     if(isChecked) {
                         RSA_tests(TEST.SIGN);
                     } else {
                         RSA_tests(TEST.VERIFY);
                     }
                 }
             }
        );
        tvOutputText2 = (TextView) findViewById(R.id.tvOutputText2);
    }

    enum TEST {
        ENCRYPT,
        DECRYPT,
        SIGN,
        VERIFY
    }

    byte[] encryptedByte;
    byte[] signatureRet;
    InputStream inPublic, inPrivate;
    PublicKey   publicKey;
    PrivateKey  privateKey;

    void RSA_tests(TEST item)
    {
        // DataUtils.getPngHexString(mAppContext);
        try {
            switch(item) {
                case ENCRYPT: // encrypt
                    inPublic = mAppContext.getAssets().open("rsa_public_testkey.pem");
                    publicKey = (PublicKey) RSAUtils.loadKey(inPublic, true);
                    byte[] src = testStr.getBytes();
                    encryptedByte = RSAUtils.encryptData(src, publicKey);
                    String path = mAppContext.getFilesDir().getAbsolutePath() + separator + "enc.bin";
                    Toast.makeText(mAppContext, path, Toast.LENGTH_LONG).show();
                    DataUtils.writeToFile(encryptedByte, path);
                    tvOutputText1.setText(DataUtils.byteArrayToHexStr(encryptedByte));
                    tvOutputText2.setText(DataUtils.byteArrayToHexStr(encryptedByte));
                    break;
                case DECRYPT: // decrypt
                    inPrivate = mAppContext.getAssets().open("rsa_private_testkey.pem");
                    privateKey = (PrivateKey) RSAUtils.loadKey(inPrivate, false);
                    byte[] decryptedByte = RSAUtils.decryptData(encryptedByte, privateKey);
                    String dest = new String(decryptedByte, "UTF-8");
                    tvOutputText1.setText(dest);
                    Toast toast = Toast.makeText(mAppContext, dest, Toast.LENGTH_LONG);
                    toast.show();
                    break;
                case SIGN: // generate signature
                    signatureRet = RSAUtils.signData(encryptedByte, privateKey);
                    path = mAppContext.getFilesDir().getAbsolutePath() + separator + "sign.bin";
                    Toast.makeText(mAppContext, path, Toast.LENGTH_LONG).show();
                    DataUtils.writeToFile(signatureRet, path);
                    tvOutputText2.setText(DataUtils.byteArrayToHexStr(signatureRet));
                    break;
                case VERIFY: // verify signature
                    if (RSAUtils.verify(encryptedByte, signatureRet, publicKey)) {
                        Toast.makeText(mAppContext,"verify success", Toast.LENGTH_LONG).show();
                        tvOutputText2.setText("verify signature success");
                    }
                    else {
                        Toast.makeText(mAppContext,"verify fail", Toast.LENGTH_LONG).show();
                        tvOutputText2.setText("verify signature fail!!!");
                    }

                    break;
                default:
                    Log.e("ERROR", "not support");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
