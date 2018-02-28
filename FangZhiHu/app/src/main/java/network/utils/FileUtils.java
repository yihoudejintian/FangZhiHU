package network.utils;

import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import network.download.DownInfo;
import okhttp3.ResponseBody;

/**
 * Created by yuantongqin on 2017/8/8.
 */

public class FileUtils {

    public void writeCaches(ResponseBody responseBody, File file, DownInfo info) {
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel channelOut = null;
            InputStream inputStream = null;
            try {
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                long allLength = 0 == info.count ? responseBody.contentLength() : info.read + responseBody
                        .contentLength();

                inputStream = responseBody.byteStream();
                randomAccessFile = new RandomAccessFile(file, "rwd");
                channelOut = randomAccessFile.getChannel();
                MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                        info.read, allLength - info.read);
                byte[] buffer = new byte[1024 * 4];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    mappedBuffer.put(buffer, 0, len);
                }
            } catch (IOException e) {
                Log.e("tests","==出错了=="+e.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (channelOut != null) {
                    channelOut.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            }
        } catch (IOException e) {
            Log.e("tests","==出错了=="+e.getMessage());
        }
    }

}
