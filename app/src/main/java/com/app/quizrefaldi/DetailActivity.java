package com.app.quizrefaldi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.quizrefaldi.databinding.ActivityDetailBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String dataNama = getIntent().getStringExtra("nama");
        String dataMember = getIntent().getStringExtra("member");
        String dataNamaBarang = getIntent().getStringExtra("namaBrg");
        int dataHargaBarang = getIntent().getIntExtra("hargaBrg", 0);
        int dataTotalHarga = getIntent().getIntExtra("totalHarga", 0);
        double dataDiskonHarga = getIntent().getDoubleExtra("diskonHarga", 0.0);
        int dataDiskonMember = getIntent().getIntExtra("diskonMember", 0);
        double dataJumlahBayar = getIntent().getDoubleExtra("totalBayar", 0.0);

        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getNumberInstance(locale);
        String total = format.format(dataJumlahBayar);
        String totalHarga = format.format(dataTotalHarga);
        String diskon = format.format(dataDiskonHarga);

        binding.tvnama.setText("Selamat Datang " + dataNama);
        binding.tvmember.setText("Tipe Member " + dataMember);
        binding.namaBrg.setText("Nama Barang : " + dataNamaBarang);
        binding.hargaBrg.setText("Harga : Rp" + format.format(dataHargaBarang));
        binding.totalHarga.setText("Total Harga : Rp" + totalHarga);
        binding.diskonHarga.setText("Diskon Harga : Rp" + diskon);
        binding.diskonMember.setText("Diskon Member : " + dataDiskonMember + "%");
        binding.totalBayar.setText("Total Bayar : Rp" + total);

        binding.btnShare.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT, "Total Bayar : Rp " + total);
            i.setType("text/plain");
            startActivity(Intent.createChooser(i, "Share Via"));
        });
    }
}