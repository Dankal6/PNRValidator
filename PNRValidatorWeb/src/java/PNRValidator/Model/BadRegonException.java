/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

/**
 * Class to throw exception when nip is wrong
 *
 * @author Daniel Kaleta
 * @version 1..0
 */
public class BadRegonException extends Exception {

    BadRegonException() {
    }

    BadRegonException(String message) {
        super(message);
    }
}
