/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author buthe_s
 */
public class DatabaseDAO {

    private String MNP_GET_SMS_PORTIN_COUNT_BY_MONTH = "select count(port_id) as Total_ports, EXTRACT(month from PORTS.RECEIVED_DATE) as months_ from MNP.MNP_COMPLETED_PORT PORTS\n"
            + " where SOURCE_SYSTEM='PortInSMS' and RECEIVED_DATE >= date '2017-01-06' \n"
            + " and status='Complete' \n"
            + " group by  extract(month from PORTS.RECEIVED_DATE);";


   

}
