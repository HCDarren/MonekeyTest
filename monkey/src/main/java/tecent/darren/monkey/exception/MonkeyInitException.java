package tecent.darren.monkey.exception;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:35 PM
 * Description: mokey sdk 初始化异常
 * History:
 * Version: 1.0.0
 */
public class MonkeyInitException extends RuntimeException{
    public MonkeyInitException(String message){
        super(message);
    }
}
