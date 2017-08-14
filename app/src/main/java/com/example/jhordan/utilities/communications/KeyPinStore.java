package com.example.jhordan.utilities.communications;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by rulosan on 8/12/17.
 */


public class KeyPinStore {
    private static KeyPinStore _instance = null;
    private final static String _crt_name = "cloudflare.crt";
    private SSLContext sslContext;
    private TrustManagerFactory trustManagerFactory;

    public static synchronized KeyPinStore getInstance(Context context) throws
            CertificateException,
            IOException,
            KeyStoreException,
            NoSuchAlgorithmException,
            KeyManagementException
    {
        if(_instance == null) {
            return new KeyPinStore(context);
        }
        return _instance;
    }

    private KeyPinStore(Context context) throws
            CertificateException,
            IOException,
            KeyStoreException,
            NoSuchAlgorithmException,
            KeyManagementException
    {
        sslContext = SSLContext.getInstance("TLS");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = new BufferedInputStream(context.getAssets().open(_crt_name));
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
        }
        finally {
            caInput.close();
        }
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null,null);
        keyStore.setCertificateEntry("ca", ca);
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
        trustManagerFactory.init(keyStore);
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    public TrustManagerFactory getTrustManagerFactory() {
        return trustManagerFactory;
    }
}
