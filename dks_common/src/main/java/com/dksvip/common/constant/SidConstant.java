package com.dksvip.common.constant;

public interface SidConstant {

    enum RightKind {

        MyKa,

        BreakfastCard;

    }


    enum Status {
        NOT_USE(0),
        HAS_LOCKED(1),
        IN_ORDER(2);


        private Integer st;

        Status(Integer st) {
            this.st = st;
        }

        public Integer getValue() {
            return st;
        }

    }







    int HAS_LOGIN = 1;

    int NOT_LOGIN = 0;


    int HAS_RIGHT = 1;

    int HAS_NOT_RIGHT = 0;

}



