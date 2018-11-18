package com.baizhi;

import java.io.Serializable;

/**
 * 请求数据对象
 */
public class RequestData implements Serializable {
    /**
     * 相同的接口对象
     */
    private Class<?> targetInterface;

    /**
     * 目标方法名
     */
    private String targetMethodName;

    /**
     * 参数列表
     *      sayHello("zs")
     */
    private Object[] args;

    /**
     * 参数列表对应的类型列表
     */
    private Class[] parameterTypes;

    public Class<?> getTargetInterface() {
        return targetInterface;
    }

    public void setTargetInterface(Class<?> targetInterface) {
        this.targetInterface = targetInterface;
    }

    public String getTargetMethodName() {
        return targetMethodName;
    }

    public void setTargetMethodName(String targetMethodName) {
        this.targetMethodName = targetMethodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public RequestData() {
    }

    public RequestData(Class<?> targetInterface, String targetMethodName, Object[] args, Class[] parameterTypes) {
        this.targetInterface = targetInterface;
        this.targetMethodName = targetMethodName;
        this.args = args;
        this.parameterTypes = parameterTypes;
    }
}
