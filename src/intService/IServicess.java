/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intService;
import Entity.Responsable;
import Entity.*;

import java.sql.SQLException;
import java.util.List;


public interface IServicess<T> {

    public abstract void add (T t) throws SQLException;
    List<T> read() throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;


    Responsable findById(int id);
}
