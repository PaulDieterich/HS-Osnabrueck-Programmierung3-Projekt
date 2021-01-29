package de.hsos.sportwetter.classes;

import android.util.Log;

import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.nio.ByteBuffer;

public class Callback extends UrlRequest.Callback {
    private static final String TAG = "UrlRequestCallback";
    private ByteBuffer buffer = ByteBuffer.allocateDirect(102400);

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        boolean shouldFollow = true; //Input prompt durch User?
        Log.i(TAG, "onRedirectReceived method called.");
        // You should call the request.followRedirect() method to continue
        // processing the request.
        if(shouldFollow) {
            request.followRedirect();
        } else {
            request.cancel();
        }
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        int httpStatusCode = info.getHttpStatusCode();
        Log.i(TAG, "onResponseStarted method called.");
        // You should call the request.read() method before the request can be
        // further processed. The following instruction provides a ByteBuffer object
        // with a capacity of 102400 bytes to the read() method.
        if(httpStatusCode == 200) {
            request.read(buffer);
        } else if (httpStatusCode == 503) {
            request.read(buffer);
        } else {
            request.cancel();
        }
        //responseHeaders = info.getAllHeaders();
    }

    @Override
    public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
        Log.i(TAG, "onReadCompleted method called.");
        // You should keep reading the request until there's no more data.
        byteBuffer.clear();
        request.read(buffer);
    }

    @Override
    public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
        Log.i(TAG, "Request succeeded.");
    }

    @Override
    public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
        Log.e(TAG, "Request failed.", error);
    }

    @Override
    public void onCanceled(UrlRequest request, UrlResponseInfo info) {
        Log.i(TAG, "Request canceled.");
        request.cancel();
        buffer.clear();
    }
}