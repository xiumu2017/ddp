package com.paradise.interview.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Paradise
 */
public class DemoInputStream extends FilterInputStream {
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    DemoInputStream(InputStream in) {
        super(in);
    }

    /**
     * 重写 read() 方法
     *
     * @return -1 返回
     * @throws IOException IO异常
     */
    @Override
    public int read() throws IOException {
        int c;
        if ((c = super.read()) > -1) {
            if (Character.isLowerCase((char) c)) {
                return Character.toUpperCase(c);
            }
        }
        System.out.print("❤");
        return super.read();
    }

}
