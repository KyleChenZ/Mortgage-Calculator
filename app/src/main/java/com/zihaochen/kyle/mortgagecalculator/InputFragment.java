package com.zihaochen.kyle.mortgagecalculator;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    EditText etHomevalue; //
    EditText etDownPayment; //
    EditText etInterestRate; //
    EditText etPropertyTaxRate; //
    TextView tvHomeError;
    TextView tvDownError;
    TextView tvInterestError;
    TextView tvPropertyError;
    Spinner sTerms; //
    OutputFragment secondFragment;
    MainActivity activity;
    Double HomeValue;
    Double DownPayment;
    Double InterestRate;
    Double PropertyTaxRate;
    Integer Terms;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        Button bReset = activity.getReset(); ; //reset button
        Button bCalculate = activity.getCalculate(); //calculate button
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        bCalculate.setOnClickListener(new View.OnClickListener(){ //
            @Override
            public void onClick(View v){
                Integer error = 0;

                secondFragment = new OutputFragment();

                etHomevalue = getView().findViewById(R.id.etHomeValue);
                etDownPayment = getView().findViewById(R.id.etDownPayment);
                etInterestRate = getView().findViewById(R.id.etInterestRate);
                etPropertyTaxRate = getView().findViewById(R.id.etPropertyTaxRate);
                tvHomeError = getView().findViewById(R.id.tvHomeError);
                tvDownError = getView().findViewById(R.id.tvDownError);
                tvInterestError = getView().findViewById(R.id.tvInterestError);
                tvPropertyError = getView().findViewById(R.id.tvPropertyError);
                //mPropertyTaxRate = getView().findViewById(R.id.mPropertyTaxRate);
                sTerms = getView().findViewById(R.id.sTerms);
                if(etHomevalue.getText().length()==0) {
                    tvHomeError.setText(" * Home Value need to be entered");
                    error++;
                }
                else{
                    HomeValue = Double.parseDouble(etHomevalue.getText().toString());
                    tvHomeError.setText("");
                    tvHomeError.setError(null);
                }
                if(etDownPayment.getText().length()==0){
                    //etDownPayment.setError("Down Payment need to be entered");
                    tvDownError.setText(" * Down Payment need to be entered");
                    error++;
                }
                else if(Double.parseDouble(etDownPayment.getText().toString()) > Double.parseDouble(etHomevalue.getText().toString())){
                    tvDownError.setText(" * Down Payment can't be larger than Home Value");
                    error++;
                }
                else{
                    DownPayment = Double.parseDouble(etDownPayment.getText().toString());
                    tvDownError.setText("");
                    tvDownError.setError(null);
                }

                if(etInterestRate.getText().length()==0){
                    //etInterestRate.setError("Interest Rate need to be entered");
                    tvInterestError.setText(" * Interest Rate need to be entered");
                    error++;
                }
                else if(Double.parseDouble(etInterestRate.getText().toString()) > 100){
                    tvInterestError.setText(" * Interest Rate is too large, try a different value");
                }
                else{
                    InterestRate = Double.parseDouble(etInterestRate.getText().toString());
                    tvInterestError.setText("");
                    tvInterestError.setError(null);
                }

                if(etPropertyTaxRate.getText().length()==0){
                    //etPropertyTaxRate.setError("Property Tax Rate need to be entered");
                    tvPropertyError.setText(" * Property Tax Rate need to be entered");
                    error++;
                }
                else if(Double.parseDouble(etPropertyTaxRate.getText().toString()) >= 1){
                    tvPropertyError.setText(" * Property Tax Rate may be too large; a typical rate is between 0.00% and 2.00%");
                }
                else{
                    PropertyTaxRate = Double.parseDouble(etPropertyTaxRate.getText().toString());
                    tvPropertyError.setText("");
                    tvPropertyError.setError(null);
                }

                if(sTerms.getSelectedItem().toString().length()==0){
                    TextView errorText = (TextView)sTerms.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("None selected");
                    error++;
                }
                else{
                    Terms = Integer.parseInt(sTerms.getSelectedItem().toString());
                    TextView errorText = (TextView)sTerms.getSelectedView();
                    errorText.setError(null);
                    errorText.clearFocus();
                }
                if(error == 0){
                    activity.sendData(HomeValue, DownPayment,InterestRate, PropertyTaxRate,Terms);
                }
                //android.support.v4.app.FragmentManager manager = getFragmentManager();
                //manager.beginTransaction()
                        //.replace(R.id.mainLayout,secondFragment)
                        //.commit();
            }
        });
        bReset.setOnClickListener(new View.OnClickListener(){ //
            @Override
            public void onClick(View v){
                etHomevalue = getView().findViewById(R.id.etHomeValue);
                etDownPayment = getView().findViewById(R.id.etDownPayment);
                etInterestRate = getView().findViewById(R.id.etInterestRate);
                etPropertyTaxRate = getView().findViewById(R.id.etPropertyTaxRate);
                tvHomeError = getView().findViewById(R.id.tvHomeError);
                tvDownError = getView().findViewById(R.id.tvDownError);
                tvInterestError = getView().findViewById(R.id.tvInterestError);
                tvPropertyError = getView().findViewById(R.id.tvPropertyError);
                sTerms = getView().findViewById(R.id.sTerms);
                //mPropertyTaxRate = getView().findViewById(R.id.mPropertyTaxRate);
                sTerms = getView().findViewById(R.id.sTerms);
                etHomevalue.setText("");
                etDownPayment.setText("");
                etInterestRate.setText("");
                etPropertyTaxRate.setText("");
                tvHomeError.setText("");
                tvDownError.setText("");
                tvInterestError.setText("");
                tvPropertyError.setText("");
                TextView errorText = (TextView)sTerms.getSelectedView();
                errorText.setError(null);
                errorText.clearFocus();
                activity.sendReset();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}
