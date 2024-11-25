package com.example.prc3_joscorrochano;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import com.example.prc3_joscorrochano.bikes.BikesContent;
import com.example.prc3_joscorrochano.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static final String NOMBRE_PREFERENCIAS = "MisPreferencias";
    private static final String CLAVE_FECHA_SELECCIONADA = "fecha_seleccionada";

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflador, ViewGroup contenedor,
            Bundle instanciaGuardada
    ) {
        binding = FragmentFirstBinding.inflate(inflador, contenedor, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View vista, Bundle instanciaGuardada) {
        super.onViewCreated(vista, instanciaGuardada);

        // Recuperar las preferencias para almacenar la fecha seleccionada
        SharedPreferences preferencias = requireContext().getSharedPreferences(NOMBRE_PREFERENCIAS, Context.MODE_PRIVATE);

        // Recuperar la fecha seleccionada de SharedPreferences
        String fechaGuardada = preferencias.getString(CLAVE_FECHA_SELECCIONADA, null);
        java.util.Calendar calendario = java.util.Calendar.getInstance();

        if (fechaGuardada != null) {

            // Si hay una fecha guardada, mostrarla y sincronizar con el CalendarView
            binding.textviewFechaSeleccionada.setText("Fecha: " + fechaGuardada);

            // Separar la fecha en día, mes y año para configurarla en el CalendarView
            String[] partes = fechaGuardada.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]) - 1; // Los meses en Calendar comienzan en 0
            int anio = Integer.parseInt(partes[2]);

            calendario.set(anio, mes, dia);
            binding.calendarViewCalendario.setDate(calendario.getTimeInMillis());

            // Actualizar la fecha seleccionada en BikesContent
            BikesContent.setSelectedDate(fechaGuardada);

        } else {

            // Si no hay una fecha guardada, usar la fecha actual
            int anio = calendario.get(java.util.Calendar.YEAR);
            int mes = calendario.get(java.util.Calendar.MONTH);
            int dia = calendario.get(java.util.Calendar.DAY_OF_MONTH);

            // Formatear la fecha actual y mostrarla
            String fechaHoy = dia + "/" + (mes + 1) + "/" + anio;
            binding.textviewFechaSeleccionada.setText("Fecha: " + fechaHoy);

            // Guardar la fecha actual en SharedPreferences como valor predeterminado
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString(CLAVE_FECHA_SELECCIONADA, fechaHoy);
            editor.apply();

            // Actualizar la fecha seleccionada en BikesContent
            BikesContent.setSelectedDate(fechaHoy);

        }

        // Listener para manejar la selección de fechas en el CalendarView
        binding.calendarViewCalendario.setOnDateChangeListener((vistaCalendario, anioSeleccionado, mesSeleccionado, diaSeleccionado) -> {

            // Crear una cadena con la fecha seleccionada
            String fechaSeleccionada = diaSeleccionado + "/" + (mesSeleccionado + 1) + "/" + anioSeleccionado;

            // Actualizar el texto mostrado
            binding.textviewFechaSeleccionada.setText("Fecha: " + fechaSeleccionada);

            // Guardar la fecha seleccionada en SharedPreferences
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString(CLAVE_FECHA_SELECCIONADA, fechaSeleccionada);
            editor.apply();

            // Actualizar la fecha seleccionada en BikesContent
            BikesContent.setSelectedDate(fechaSeleccionada);

        });

        // Listener para el botón que confirma la fecha seleccionada
        binding.buttonElegirDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vistaBoton) {

                // Recuperar la fecha seleccionada de SharedPreferences
                String fechaSeleccionada = preferencias.getString(CLAVE_FECHA_SELECCIONADA, null);

                // Comprobar que la fecha seleccionada sea null
                if (fechaSeleccionada == null) {

                    // Mostrar error si no hay fecha seleccionada
                    binding.textviewFechaSeleccionada.setText("No se ha seleccionado fecha.");

                } else {

                    // Crear un Bundle para pasar la fecha seleccionada al siguiente fragmento
                    Bundle bundle = new Bundle();
                    bundle.putString("fecha_seleccionada", fechaSeleccionada);

                    // Configurar las animaciones personalizadas para la navegación
                    NavOptions opcionesNavegacion = new NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in_right)
                            .setExitAnim(R.anim.slide_out_left)
                            .setPopEnterAnim(R.anim.slide_in_left)
                            .setPopExitAnim(R.anim.slide_out_right)
                            .build();

                    // Navegar al segundo fragmento con el Bundle y las animaciones
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle, opcionesNavegacion);

                }

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}