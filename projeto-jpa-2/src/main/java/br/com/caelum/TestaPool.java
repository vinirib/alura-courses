package br.com.caelum;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestaPool {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        ComboPooledDataSource dataSource = (ComboPooledDataSource) new JpaConfigurator().getDataSource();

        dataSource.getConnection();

        System.out.println("Conexões existentes: " + dataSource.getNumConnections());
        System.out.println("Conexões ocupadas: " + dataSource.getNumBusyConnections());
        System.out.println("Conexões ociosas: " + dataSource.getNumIdleConnections());

    }
}
