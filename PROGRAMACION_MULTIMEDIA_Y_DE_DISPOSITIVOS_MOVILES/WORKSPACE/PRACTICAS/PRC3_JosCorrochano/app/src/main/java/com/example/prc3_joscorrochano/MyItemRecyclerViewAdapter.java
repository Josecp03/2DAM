package com.example.prc3_joscorrochano;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prc3_joscorrochano.bikes.BikesContent;
import com.example.prc3_joscorrochano.databinding.FragmentItemBinding;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<BikesContent.Bike> mValues;
    public MyItemRecyclerViewAdapter(List<BikesContent.Bike> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentItemBinding binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // Obtener el objeto Bike correspondiente a la posición actual
        BikesContent.Bike bike = mValues.get(position);

        // Asignar los valores a las vistas utilizando binding
        holder.binding.TextViewCiudad.setText(bike.getCity());
        holder.binding.TextViewPropietario.setText(bike.getOwner());
        holder.binding.TextViewUbicacion.setText(bike.getLocation());
        holder.binding.TextViewDescripcion.setText(bike.getDescription());
        holder.binding.imageViewFotoBici.setImageBitmap(bike.getPhoto());

        // Configurar la acción del botón de correo
        holder.binding.ImageButtonEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Asignar contexto
                Context context = view.getContext();

                // Crear el intent
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                // Configurar el tipo
                emailIntent.setType("message/rfc822");

                // Configurar lo que le voy a pasar al mail
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{bike.getEmail()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bike Request");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear " + bike.getOwner() + ",\n\n"
                        + "I'd like to use your bike at " + bike.getLocation() + " (" + bike.getCity() + ") "
                        + "for the following date: " + BikesContent.getSelectedDate() + "\n\n"
                        + "Can you confirm its availability?\nKindest regards,");

                // Manejar posibles errores al intentar abrir un cliente de correo
                try {

                    // Mostrar un diálogo para que el usuario elija una aplicación de correo
                    context.startActivity(Intent.createChooser(emailIntent, "Send email..."));

                } catch (android.content.ActivityNotFoundException ex) {

                    // Mostrar un mensaje emergente si no hay aplicaciones de correo instaladas
                    Toast.makeText(context, "No hay aplicaciones de correo existentes", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final FragmentItemBinding binding;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
