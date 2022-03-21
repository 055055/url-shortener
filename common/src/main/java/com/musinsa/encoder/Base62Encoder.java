package com.musinsa.encoder;

import com.musinsa.error.CommonException;

public abstract class Base62Encoder {
    protected static final String BASE62_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public abstract String encode(int seq) throws CommonException;

    public abstract int decode(String value) throws CommonException;
}
