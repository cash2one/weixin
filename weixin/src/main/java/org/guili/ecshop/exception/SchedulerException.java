
package org.guili.ecshop.exception;

public class SchedulerException extends RuntimeException {

	private static final long serialVersionUID = -3646892563559646704L;

	public SchedulerException() {
		super();
	}

	public SchedulerException(String message) {
		super(message);
	}

	public SchedulerException(Throwable cause) {
		super(cause);
	}

	public SchedulerException(String message, Throwable cause) {
		super(message, cause);
	}
}
