package network.intercepter;


import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;


import com.tongqinyuan.fangzhihu.BuildConfig;
import com.tongqinyuan.fangzhihu.common.LogUtil;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import network.DownResponseBody;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by yuantongqin on 2017/8/7.
 */

public class BaseIntercepter implements Interceptor {
    public static final String TAG = BaseIntercepter.class.getSimpleName();
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private String mType;
    Map<String, Object> mHeader;

    public BaseIntercepter(Map<String, Object> header, String type) {
//        this.mHeader = header;
        this.mType = type;
    }

    public BaseIntercepter(String type) {
        this.mType = type;
    }

    public BaseIntercepter() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (BuildConfig.DEBUG) {
            if (!"get".equalsIgnoreCase(request.method())) {
                logRequest(request);
            }
        }
        Request.Builder builder = chain.request().newBuilder();
        mHeader = addHeader();
        LogUtil.e(TAG, mHeader.toString() + "===请求头部信息==" + BuildConfig.DEBUG);
        if (mHeader != null && mHeader.size() > 0) {
            if (mHeader != null && mHeader.size() > 0) {
                Set<String> keySet = mHeader.keySet();
                for (String key : keySet) {
                    String value = mHeader.get(key) == null ? "" : mHeader.get(key).toString();
                    builder.addHeader(key, value);
                }
            }
        }
        Request build = builder.build();
        Log.e(TAG, "==type==" + mType);
        if ("download".equalsIgnoreCase(mType)) {
            Response response = chain.proceed(build);
            return response.newBuilder().body(new DownResponseBody(response.body())).build();
        }
        return chain.proceed(build);
    }

    public static Map<String, Object> addHeader() {
        Map<String, Object> mHeader = new HashMap<>();
        mHeader.put("platform", "android(" + Build.VERSION.RELEASE + ")");
        mHeader.put("network", "Wi-Fi");
        mHeader.put("model", Build.MODEL);
        mHeader.put("apiversion", "1.0");
        mHeader.put("app_version", "1.0.0");
//        mHeader.put("authtokenpl", LocalDataRepository.getInstance().getAuthToken());
//        mHeader.put("apkchannel", DeviceUtil.getApkUMChannel());
//        mHeader.put("deviceId", SmAntiFraud.getDeviceId());//数美设备指纹
        return mHeader;
    }

    public void logRequest(Request request) {
        StringBuilder sb = new StringBuilder();
        RequestBody body = request.body();

        if (body != null) {
            try {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (isPlaintext(buffer)) {
                    assert charset != null;
                    LogUtil.e(TAG, buffer.readString(charset));
                    LogUtil.e(TAG, "--> END " + request.method()
                            + " (" + body.contentLength() + "-byte body)");
                } else {
                    LogUtil.e(TAG, "--> END " + request.method() + " (binary "
                            + body.contentLength() + "-byte body omitted)");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (request.body() instanceof FormBody) {
            FormBody oldFormBody = (FormBody) request.body();
            assert oldFormBody != null;
            for (int i = 0; i < oldFormBody.size(); i++) {
                sb.append(oldFormBody.encodedName(i)).append("=key=value==").append(oldFormBody.encodedValue(i));
            }
            LogUtil.d(sb.toString());
        }
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

}
