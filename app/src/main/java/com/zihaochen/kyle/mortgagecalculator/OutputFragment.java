package com.zihaochen.kyle.mortgagecalculator;


import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Date;
import java.util.Locale;

import static java.lang.Math.pow;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutputFragment extends Fragment {
    TextView tvMonthlyPayAmount;
    TextView tvTotalInterestPaid;
    TextView tvTotalPropertyTaxPaid;
    TextView tvPayOffDate;
    TextView tvMonthlyPayAmountWithPropertyTax;
    public OutputFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return inflater.inflate(R.layout.fragment_output, container, false);
    }
    public void calculate(Double HomeValue,Double DownPayment, Double InterestRate, Double PropertyTaxRate, Integer Terms){
        String month = new SimpleDateFormat("MM", Locale.US).format(new Date());
        String year = new SimpleDateFormat("yyyy",Locale.US).format(new Date());
        Integer monthInt;
        if(Integer.parseInt(month) == 1) monthInt = 12;
        else{
            monthInt = Integer.parseInt(month) - 1;
        }

        tvMonthlyPayAmount = getView().findViewById(R.id.tvMonthlyPayAmount);
        tvTotalInterestPaid = getView().findViewById(R.id.tvTotalInterestPaid);
        tvTotalPropertyTaxPaid = getView().findViewById(R.id.tvTotalPropertyTaxPaid);
        tvPayOffDate = getView().findViewById(R.id.tvPayOffDate);
        tvMonthlyPayAmountWithPropertyTax = getView().findViewById(R.id.tvMonthlyPayAmountWP);

        Integer yearInt = Integer.parseInt(year) + Terms;
        String Payoffdate = monthInt.toString() + "/" + yearInt.toString();
        Double Rate = (InterestRate / 100) / 12;
        Double pv = HomeValue - DownPayment;
        Integer nper = 12 * Terms;
        Double MonthlyPayment = pv * (Rate * pow((1 + Rate), nper)) / (pow((1 + Rate), nper) - 1);
        Double TotalPayment = MonthlyPayment * 12 * Terms;
        Double TotalPropertyTaxPaid = HomeValue * PropertyTaxRate/100 * Terms;
        Double TotalInterestPaid = TotalPayment - pv;
        Double MonthlyPaymentWithProperty = MonthlyPayment + TotalPropertyTaxPaid/Terms/12;
        String mp = "$" + MonthlyPayment;
        String mpp = "$" + MonthlyPaymentWithProperty;
        String ip = "$" + TotalInterestPaid;
        String ptp = "$" + TotalPropertyTaxPaid;

        tvMonthlyPayAmountWithPropertyTax.setText(mpp);
        tvMonthlyPayAmount.setText(mp);
        tvTotalInterestPaid.setText(ip);
        tvTotalPropertyTaxPaid.setText(ptp);
        tvPayOffDate.setText(Payoffdate);


    }
    public void reset(){
        String date = new SimpleDateFormat("MM/yyyy", Locale.US).format(new Date());
        tvMonthlyPayAmount = getView().findViewById(R.id.tvMonthlyPayAmount);
        tvTotalInterestPaid = getView().findViewById(R.id.tvTotalInterestPaid);
        tvTotalPropertyTaxPaid = getView().findViewById(R.id.tvTotalPropertyTaxPaid);
        tvMonthlyPayAmountWithPropertyTax = getView().findViewById(R.id.tvMonthlyPayAmountWP);
        tvPayOffDate = getView().findViewById(R.id.tvPayOffDate);
        tvMonthlyPayAmount.setText("$0");
        tvTotalInterestPaid.setText("$0");
        tvTotalPropertyTaxPaid.setText("$0");
        tvMonthlyPayAmountWithPropertyTax.setText("$0");
        tvPayOffDate.setText(date);
    }
}
