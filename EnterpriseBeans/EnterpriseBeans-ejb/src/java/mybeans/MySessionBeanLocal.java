/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybeans;

import javax.ejb.Local;

/**
 *
 * @author fernandasramirezm
 */
@Local
public interface MySessionBeanLocal {

    int add(int a, int b);
    
}
