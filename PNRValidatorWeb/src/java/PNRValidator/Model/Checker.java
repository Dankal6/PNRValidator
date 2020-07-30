/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

/**
 * @author Daniel Kaleta
 * @version 1.2.0
 */
public interface Checker {
    public boolean check(String number) throws Exception;
    public int calcCheckSum(int tab[]);
}
