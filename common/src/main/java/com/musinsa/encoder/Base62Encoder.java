package com.musinsa.encoder;

public abstract class Base62Encoder {
    protected static final String BASE62_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public abstract String encode(int seq);

    public abstract int decode(String value);
}
