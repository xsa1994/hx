package com.hx.util;

/**
 * Created by huangch on 2019/8/8 20:07
 * description:
 *
 * @since JDK 1.6
 */
public class HxException extends Exception {

    private static final long serialVersionUID = 6996125956350664170L;

    private String errorMassage;

    public HxException(String errorMassage) {
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }
}
