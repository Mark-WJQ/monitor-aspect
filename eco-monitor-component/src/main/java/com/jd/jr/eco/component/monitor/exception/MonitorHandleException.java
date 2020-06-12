package com.jd.jr.eco.component.monitor.exception;

/**
 *  对被监控方法返回结果或跑出异常处理时，可能会发生异常情况，发生的异常使用该异常封装后抛出
 *
 * @author wangjianqiang24
 * @date 2020/6/11
 */
public class MonitorHandleException extends Exception {
    private static final long serialVersionUID = 9073953097716686916L;

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public MonitorHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public MonitorHandleException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MonitorHandleException(String message) {
        super(message);
    }
}
