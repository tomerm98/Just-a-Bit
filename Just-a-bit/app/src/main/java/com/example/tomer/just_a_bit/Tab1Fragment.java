package com.example.tomer.just_a_bit;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Tomer on 27/01/2017.
 */

public class Tab1Fragment extends Fragment implements ZXingScannerView.ResultHandler, View.OnClickListener {
    Button test;
    ZXingScannerView scanner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        test = (Button) getView().findViewById(R.id.btnTest);

        test.setOnClickListener(this);
        scanner = new ZXingScannerView(getContext());
        scanner.setResultHandler(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        scanner.stopCamera();

    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();
        scanner.stopCamera();
        getActivity().setContentView(R.layout.activity_main);
        ((MainActivity)getActivity()).test();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnTest:
                onClickBtnTest(view);
        }
    }

    private void onClickBtnTest(View view) {

        getActivity().setContentView(scanner);

        scanner.startCamera();
    }
}
