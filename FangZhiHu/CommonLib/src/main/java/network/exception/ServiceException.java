package network.exception;

/**
 * Created by yuantongqin on 2017/8/15.
 * modify by liyanxi on 2017/9/27
 * todo 新增参数data，考虑到请求状态不为0时，后端也有信息返回需要根据实际情况解析
 */

public class ServiceException extends RuntimeException {
    //服务器运行时异常
    private int code;
    public int customState;
    private String message;
    private String data;

    public ServiceException(int code, String msg, String data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ServiceException(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public ServiceException(int customState,int code, String data) {
        this.customState = customState;
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }


}
