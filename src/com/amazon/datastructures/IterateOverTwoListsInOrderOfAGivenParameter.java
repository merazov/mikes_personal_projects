package com.amazon.datastructures;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class IterateOverTwoListsInOrderOfAGivenParameter {

    // Visitor pattern!
    interface VisitorInterface {
        public void visit(A visitor);
        public void visit(B visitor);
    }
    
    static class ConcreteVisitor1 implements VisitorInterface {
        @Override
        public void visit(A visitor) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void visit(B visitor) {
            // TODO Auto-generated method stub
            
        }
    }

    static class ConcreteVisitor2 implements VisitorInterface {
        @Override
        public void visit(A visitor) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void visit(B visitor) {
            // TODO Auto-generated method stub
            
        }
    }
    
    interface VisitedInterface {
        public void accept(VisitorInterface visitor);
    }
    
    @RequiredArgsConstructor
    @Data
    static class A implements VisitedInterface {
        private final Integer startDate;

        @Override
        public void accept(VisitorInterface visitor) {
            visitor.visit(this);
        }
    }

    @RequiredArgsConstructor
    static class B implements VisitedInterface {
        private final Integer startDate;
        
        public void visit(VisitorInterface visitor) {
            visitor.visit();
        }
    }

    public static void main(String[] args) {

    }
}
