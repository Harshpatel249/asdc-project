package com.group6.commune.Security;

import com.group6.commune.Security.KeyGenerator;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RSAKeys {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAKeys(){
        KeyPair pair = KeyGenerator.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }

    public RSAPublicKey getPublicKey(){
        return this.publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey){
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey(){
        return this.privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey){
        this.privateKey = privateKey;
    }
}
