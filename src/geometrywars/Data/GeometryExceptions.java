/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Data;

/**
 *
 * @author oliver
 */
public class GeometryExceptions extends RuntimeException {

    public GeometryExceptions(String msg) {
        super(msg);
    }

    public GeometryExceptions(String msg, Throwable cause) {
        super(msg,cause);
    }
    
}
