package com.shushper.loftmoneyjan01;

import com.google.gson.annotations.SerializedName;

public class BalanceResponse {

    @SerializedName("total_income")
    private int totalIncome;

    @SerializedName("total_expenses")
    private int totalExpense;


    public BalanceResponse(int totalIncom, int totalExpense) {
        this.totalIncome = totalIncom;
        this.totalExpense = totalExpense;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(int totalExpense) {
        this.totalExpense = totalExpense;
    }
}


