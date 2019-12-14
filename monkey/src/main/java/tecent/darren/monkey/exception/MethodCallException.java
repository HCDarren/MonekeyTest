package tecent.darren.monkey.exception;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 11:26 AM
 * Description: monkey 方法调用异常
 * Version: 1.0.0
 */
public class MethodCallException extends RuntimeException{
    public MethodCallException(String message){
        super(message);
    }
}
