package com.example.lab08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab08.model.ItemList;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgItemDetail;
    private TextView tvNombreDetail;
    private TextView tvEmailDetail;
    private ItemList itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();

    }
    private void initViews(){
        imgItemDetail = findViewById(R.id.imgItemDetail);
        tvNombreDetail=findViewById(R.id.tvNombreDetail);
        tvEmailDetail = findViewById(R.id.tvEmailDetail);
    }
    private void initValues(){
        itemDetail = (ItemList) getIntent().getExtras().getSerializable("itemDetail");
        imgItemDetail.setImageResource(itemDetail.getImgResource());
        tvNombreDetail.setText(itemDetail.getNombre());
        tvEmailDetail.setText(itemDetail.getEmail() );


    }
}