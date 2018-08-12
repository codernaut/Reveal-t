package org.cfp.cilc.revealit.accessors;


import com.loopj.android.http.AsyncHttpResponseHandler;

import org.cfp.cilc.revealit.gui.TextAnalysisFragment;

import cz.msebera.android.httpclient.Header;

public class FakeboxAccessor extends AsyncHttpResponseHandler {

    private final TextAnalysisFragment fragment;

    public FakeboxAccessor(TextAnalysisFragment fragment) {
        this.fragment=fragment;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        fragment.success(new String(responseBody));

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        fragment.fail(new String(responseBody));

    }
}
