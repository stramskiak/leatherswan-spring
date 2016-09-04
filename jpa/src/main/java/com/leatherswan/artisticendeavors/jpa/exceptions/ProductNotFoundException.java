
    package com.leatherswan.artisticendeavors.jpa.exceptions;

    public class ProductNotFoundException extends Exception {

        private static final long serialVersionUID = -8060531120470573530L;

        private String msg;

        public ProductNotFoundException() {
            super();
        }

        public ProductNotFoundException(String msg) {
            this.msg = System.currentTimeMillis()
                    + ": " + msg;
        }

        public String getMsg() {
            return msg;
        }


    }


