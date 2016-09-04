package com.leatherswan.artisticendeavors.jpa.exceptions;

/**
 * Modified by stramskiak
 */
public class TagNotFoundException extends Exception {

    private static final long serialVersionUID = -9070542392426394574L;
    private String msg;

    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(String msg) {
        this.msg = System.currentTimeMillis()
                + ": " + msg;
    }

    public String getMsg() {
        return msg;
    }


}
