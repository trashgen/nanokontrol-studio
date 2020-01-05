package ru.utoplov.vladimir.core;

abstract public class AbstractControl implements Control {

    protected ControllerContext cc;

    public AbstractControl(ControllerContext cc) {
        this.cc = cc;
    }

}
