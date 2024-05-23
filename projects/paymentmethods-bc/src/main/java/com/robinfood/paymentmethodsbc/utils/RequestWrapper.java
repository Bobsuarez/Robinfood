package com.robinfood.paymentmethodsbc.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final class ServletInputStreamExtension extends ServletInputStream {
        private final ByteArrayInputStream byteArrayInputStream;

        private ServletInputStreamExtension(
            ByteArrayInputStream byteArrayInputStream
        ) {
            this.byteArrayInputStream = byteArrayInputStream;
        }

        public int read() {
            return byteArrayInputStream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            //
        }
    }

    private final String body;
    private static final int BUFFER_READER_LENGTH = 128;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream,
                Charset.defaultCharset()
            );
            bufferedReader = new BufferedReader(inputStreamReader);

            char[] charBuffer = new char[BUFFER_READER_LENGTH];
            int bytesRead = -1;

            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
        body = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
            body.getBytes(Charset.defaultCharset())
        );
        return new ServletInputStreamExtension(byteArrayInputStream);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(
            this.getInputStream(),
            Charset.defaultCharset()
        );
        return new BufferedReader(inputStreamReader);
    }

    //Use this method to read the request body N times
    public String getBody() {
        return this.body;
    }
}
