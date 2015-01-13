package com.gffunds.sercurity.xml;

public class SignatureNotFound extends RuntimeException {

    public SignatureNotFound() {
        super("Cannot find Signature element.");
    }
}
