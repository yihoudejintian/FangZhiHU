package network.exception;

/**
 * Created by yuantongqin on 2017/8/15.
 */

public class SendException extends Exception {

    private int code;
    private String msg;
    private String data;
    public int customState;

    public SendException(Throwable throwable, int code) {
        super(throwable);
    }

    public SendException(Throwable throwable, int code, String msg) {
        super(throwable);
        this.code = code;
        this.msg = msg;
    }
    public SendException(Throwable throwable,int customState, int code, String msg, String data) {
        super(throwable);
        this.customState = customState;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public SendException(Throwable throwable, int code, String msg, String data) {
        super(throwable);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }


}
