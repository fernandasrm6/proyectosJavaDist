/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybeans;

import javax.ejb.Stateless;

/**
 *
 * @author fernandasramirezm
 */
@Stateless
public class MySessionBean implements MySessionBeanLocal {

    @Override
    public int add(int a, int b) {
        return a+b;
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
