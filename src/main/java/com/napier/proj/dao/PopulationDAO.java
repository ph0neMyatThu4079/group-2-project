package com.napier.proj.dao;

import java.sql.Connection;

public class PopulationDAO {
    private Connection con;

    public PopulationDAO(Connection con) {
        this.con = con;
    }

}
