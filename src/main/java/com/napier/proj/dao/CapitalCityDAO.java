package com.napier.proj.dao;

import com.napier.proj.model.CapitalCity;

import java.sql.Connection;
import java.util.ArrayList;

public class CapitalCityDAO {

    private Connection con;

    public CapitalCityDAO(Connection con) {
        this.con = con;
    }


//    The top N populated capital cities in a region where N is provided by the user.
//    public ArrayList<CapitalCity> getTopNPopulatedCitiesInRegion(String region, int n) {
//        ArrayList<CapitalCity> capitalCityArrayList = new ArrayList<>();
//
//        String sql = "SELECT city.Name As Capacity"
//    }

}
