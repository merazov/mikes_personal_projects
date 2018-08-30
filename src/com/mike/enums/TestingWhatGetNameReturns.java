package com.mike.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TestingWhatGetNameReturns {

    //@Getter
    @AllArgsConstructor
    private enum RunTypes {
        /*CONSTRAINED_PALLETIZED("ConstrainedPalletized"),
        CONSTRAINED("Constrained"),
        UNCONSTRAINED_COMPONENTS("UnconstrainedComponents"),
        UNCONSTRAINED("Unconstrained"),
        NONE("None");*/
        
        CONSTRAINED_PALLETIZED(),
        CONSTRAINED(),
        UNCONSTRAINED_COMPONENTS(),
        UNCONSTRAINED(),
        NONE();

        //private final String name;
    }

    public static void main(String[] args) {
        System.out.println("name=" + RunTypes.CONSTRAINED_PALLETIZED.name());
    }
}
