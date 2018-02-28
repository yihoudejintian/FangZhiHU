package network;


import java.io.IOException;

import network.download.DownInfo;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by yuantongqin on 2017/8/8.
 */

public class DownResponseBody extends ResponseBody {

    private final ResponseBody mResponseBody;
    private BufferedSource bufferedSource;
    private final DownInfo mDownInfo;

    public DownResponseBody(ResponseBody responseBody){
        this.mResponseBody = responseBody;

        mDownInfo = new DownInfo("","");
    }
    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {

        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        Source s =  new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                mDownInfo.read = totalBytesRead;
                mDownInfo.count = mResponseBody.contentLength();
                mDownInfo.complete = bytesRead == -1;
                RxBus.getInstance().OnEventPost(mDownInfo);
                return bytesRead;
            }
        };

        return s;

    }
}
