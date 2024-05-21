package example.writeflush;

/**
 * @author chenx
 * @create 2023-08-21 16:50
 */
public class ResponseSample {
    private String code;
    private String data;
    private long time;

    public ResponseSample() {
    }

    public ResponseSample(String code, String data, long time) {
        this.code = code;
        this.data = data;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public long getTime() {
        return time;
    }
}
