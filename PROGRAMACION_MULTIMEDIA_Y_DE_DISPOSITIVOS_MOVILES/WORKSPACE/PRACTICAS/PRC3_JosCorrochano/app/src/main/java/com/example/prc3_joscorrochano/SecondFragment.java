package com.example.prc3_joscorrochano;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prc3_joscorrochano.bikes.BikesContent;
import com.example.prc3_joscorrochano.databinding.FragmentSecondBinding;
import com.example.prc3_joscorrochano.placeholders.PlaceholderAdapter;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar RecyclerView con layout manager
        binding.recyclerViewBikes.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Mostrar placeholders mientras los datos cargan
        PlaceholderAdapter placeholderAdapter = new PlaceholderAdapter(10);
        binding.recyclerViewBikes.setAdapter(placeholderAdapter);

        // Simular la carga de datos
        new Handler().postDelayed(() -> {

            // Cargar las bicicletas desde el JSON
            BikesContent.loadBikesFromJSON(requireContext());

            // Configurar el adaptador real del RecyclerView con los datos
            binding.recyclerViewBikes.setAdapter(new MyItemRecyclerViewAdapter(BikesContent.ITEMS));

        }, 1000); // Simular un retraso de 1 segundo
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
