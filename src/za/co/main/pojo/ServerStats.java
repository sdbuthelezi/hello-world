/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.main.pojo;

import java.util.ArrayList;

/**
 *
 * @author buthe_s
 */
public class ServerStats {
    
    private String serverName, serverType;
    private ArrayList<monthlyStats> monthStats;
    
    class monthlyStats{
        
        private String month;
        private double maxCPU, minCPU, avgCPU, maxMem, minMem, avgMem;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public double getMaxCPU() {
            return maxCPU;
        }

        public void setMaxCPU(double maxCPU) {
            this.maxCPU = maxCPU;
        }

        public double getMinCPU() {
            return minCPU;
        }

        public void setMinCPU(double minCPU) {
            this.minCPU = minCPU;
        }

        public double getAvgCPU() {
            return avgCPU;
        }

        public void setAvgCPU(double avgCPU) {
            this.avgCPU = avgCPU;
        }

        public double getMaxMem() {
            return maxMem;
        }

        public void setMaxMem(double maxMem) {
            this.maxMem = maxMem;
        }

        public double getMinMem() {
            return minMem;
        }

        public void setMinMem(double minMem) {
            this.minMem = minMem;
        }

        public double getAvgMem() {
            return avgMem;
        }

        public void setAvgMem(double avgMem) {
            this.avgMem = avgMem;
        }
        
        
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public ArrayList<monthlyStats> getMonthStats() {
        return monthStats;
    }

    public void setMonthStats(ArrayList<monthlyStats> monthStats) {
        this.monthStats = monthStats;
    }
    
    
    
}
