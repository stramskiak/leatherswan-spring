package com.leatherswan.artisticendeavors.jpa.exceptions;

public class ItemNotFoundException extends Exception {

    private static final long serialVersionUID = -8060531120470573530L;

    private String msg;

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String msg) {
        this.msg = System.currentTimeMillis()
                + ": " + msg;
    }

    public String getMsg() {
        return msg;
    }


}
