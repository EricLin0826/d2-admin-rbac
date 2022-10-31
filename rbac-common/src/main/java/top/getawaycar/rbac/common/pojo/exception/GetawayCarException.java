package top.getawaycar.rbac.common.pojo.exception;

/**
 * 本地异常
 *
 * @author EricJeppesen
 * @date 2022/10/14 20:18
 */
public class GetawayCarException extends RuntimeException {
    private int status;

    public GetawayCarException() {
    }

    public GetawayCarException(String msg) {
        super(msg);
    }

    public GetawayCarException(String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params));
    }

    public GetawayCarException(Throwable throwable) {
        super(throwable);
    }

    public GetawayCarException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public GetawayCarException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    public GetawayCarException(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public GetawayCarException(int status, Throwable throwable) {
        super(throwable);
        this.status = status;
    }

    public GetawayCarException(int status, String msg, Throwable throwable) {
        super(msg, throwable);
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

}
