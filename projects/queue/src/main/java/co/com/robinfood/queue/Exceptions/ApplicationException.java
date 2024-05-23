/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.Exceptions;

/**
 * @author Bobsu
 */
public class ApplicationException extends RuntimeException {

    protected int code;
    protected String errorMessage;

    public ApplicationException(int code, String errorMessage) {
        super(errorMessage);
        this.code = this.code;
    }

    public String getError() {
        return this.errorMessage;
    }
}
