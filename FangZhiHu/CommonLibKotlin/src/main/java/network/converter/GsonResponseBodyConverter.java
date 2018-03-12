package network.converter;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import network.entity.ApiResponse;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tongqinyuan on 2017/11/4.
 */

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = GsonResponseBodyConverter.class.getCanonicalName();
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        ApiResponse apiResponse = gson.fromJson(string, ApiResponse.class);
        Log.e(TAG,"string=="+string);
//        if (apiResponse.getErrorCode() != 0) {
//            value.close();
////            throw new ServiceException(ErrorCode.ERROR_STATE_ZERO,apiResponse.getErrorCode(),string);
//        }

        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(Charset.defaultCharset()):Charset.defaultCharset();
        InputStream stream = new ByteArrayInputStream(string.getBytes());
        assert charset != null;
        Reader reader = new InputStreamReader(stream,charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
//        JsonReader jsonReader = this.gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}