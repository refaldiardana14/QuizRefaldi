package com.app.quizrefaldi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.quizrefaldi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnProses.setOnClickListener(view -> {
            String item;
            String member = "";
            String nama = binding.edNama.getText().toString();
            String namaBrg = binding.edkodeBarang.getText().toString();
            int hargaBrg = 0;
            int diskon = 0;
            int jumlahBarang = Integer.parseInt(binding.edJumlahBarang.getText().toString());

            if (binding.edgold.isChecked()) {
                diskon = 10;
                member = "Gold";
            } else if (binding.edsilver.isChecked()) {
                diskon = 5;
                member = "Silver";
            } else if (binding.edbiasa.isChecked()) {
                diskon = 2;
                member = "Biasa";
            }

            switch (namaBrg) {
                case "PCO":
                    hargaBrg = 2730551;
                    item = "POCO M3";
                    break;
                case "OAS":
                    hargaBrg = 1989123;
                    item = "Oppo A5S";
                    break;
                case "AA5":
                    hargaBrg = 9999999;
                    item = "Acer Aspire 5";
                    break;
                default:
                    item = "";
                    break;
            }

            if (nama.isEmpty()) {
                Toast.makeText(MainActivity.this, "Nama Kosong...", Toast.LENGTH_SHORT).show();
            } else if (diskon == 0) {
                Toast.makeText(MainActivity.this, "Diskon Kosong...", Toast.LENGTH_SHORT).show();
            } else if (item.isEmpty()) {
                Toast.makeText(MainActivity.this, "Barang Kosong...", Toast.LENGTH_SHORT).show();
            } else if (jumlahBarang == 0) {
                Toast.makeText(MainActivity.this, "Jumlah Barang Kosong...", Toast.LENGTH_SHORT).show();
            } else {
                double diskonHarga = hargaBrg * (diskon / 100.0);
                int totalHarga = hargaBrg * jumlahBarang;
                double totalBayar = hargaBrg - diskonHarga * jumlahBarang;

                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("nama", nama);
                i.putExtra("member", member);
                i.putExtra("namaBrg", namaBrg);
                i.putExtra("hargaBrg", hargaBrg);
                i.putExtra("totalHarga", totalHarga);
                i.putExtra("diskonHarga", diskonHarga);
                i.putExtra("diskonMember", diskon);
                i.putExtra("totalBayar", totalBayar);
                startActivity(i);
            }
        });
    }
}