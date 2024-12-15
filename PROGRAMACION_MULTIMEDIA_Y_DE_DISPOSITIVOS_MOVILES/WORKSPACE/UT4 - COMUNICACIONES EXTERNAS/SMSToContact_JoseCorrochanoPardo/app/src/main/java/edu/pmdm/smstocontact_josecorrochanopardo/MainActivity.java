package edu.pmdm.smstocontact_josecorrochanopardo;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout lyNombre = null;
    private LinearLayout lyApellido = null;
    private LinearLayout lyContactos = null;
    private EditText edtMensaje = null;
    private Button btnEnviarSMS = null;
    private EditText edtNombre = null;
    private EditText edtApellido = null;
    private ImageButton imgbBuscarNombre = null;
    private ImageButton imgBuscarApellido = null;
    private Button btnSeleccionarContacto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignar valores XML
        lyNombre = findViewById(R.id.LinearLayoutNombre);
        lyApellido = findViewById(R.id.LinearLayoutApellido);
        lyContactos = findViewById(R.id.LinearLayoutContactos);
        edtMensaje = findViewById(R.id.editTextMensaje);
        btnEnviarSMS = findViewById(R.id.buttonEnviarSMS);
        edtNombre = findViewById(R.id.editTextNombre);
        edtApellido = findViewById(R.id.editTextApellido);
        imgbBuscarNombre = findViewById(R.id.imageButtonSearchNombre);
        imgBuscarApellido = findViewById(R.id.imageButtonSearchApellido);
        btnSeleccionarContacto = findViewById(R.id.buttonSeleccionarContacto);

        // Ocultar componentes que al principio no son necesarios
        lyNombre.setVisibility(View.INVISIBLE);
        lyApellido.setVisibility(View.INVISIBLE);
        lyContactos.setVisibility(View.INVISIBLE);
        edtMensaje.setVisibility(View.INVISIBLE);
        btnEnviarSMS.setVisibility(View.INVISIBLE);

        // Listener para cuando se pulse el botón de seleciconar contacto
        btnSeleccionarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hacer visibles los componentes correspondientes
                lyNombre.setVisibility(View.VISIBLE);
                lyApellido.setVisibility(View.VISIBLE);

            }
        });

        // Listener para cuando se pulse el botón de buscar por nombre
        imgbBuscarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realizarBusquedaContactos();

            }
        });

        // Listener para cuando se pulsa el botón de buscar por apellido
        imgBuscarApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realizarBusquedaContactos();

            }
        });



    }

    private void realizarBusquedaContactos() {

        lyContactos.setVisibility(View.VISIBLE);

        // Comprobar que los dos campos estén vacíos
        if (camposVacios()) {

            mostrarTodosLosContactos();

        }

        lyContactos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Hacer visible lo demás
                edtMensaje.setVisibility(View.VISIBLE);
                btnEnviarSMS.setVisibility(View.VISIBLE);

                return false;
            }
        });

        btnEnviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobar que haya mensaje
                if (!edtMensaje.getText().toString().isEmpty()) {

                    // Resetear los campos
                    edtNombre.setText("");
                    edtApellido.setText("");
                    lyContactos.setVisibility(View.INVISIBLE);
                    edtMensaje.setText("");
                    edtMensaje.setVisibility(View.INVISIBLE);
                    btnEnviarSMS.setVisibility(View.INVISIBLE);

                    // Crear el diálogo
                    AlertDialog dialogoVistaConactos = crearDialogoVistaContacto();

                    // Mostrar el diálogo
                    dialogoVistaConactos.show();

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "No puedes enviar un SMS sin mensaje", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    private void mostrarTodosLosContactos() {
        // Proyección para limitar las columnas que necesitamos
        String[] proyeccion = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };

        // Obtener el ContentResolver para consultar los contactos
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                proyeccion, // Solo las columnas especificadas
                null,       // Sin filtro, obtendremos todos los contactos
                null,
                null        // Sin orden específico
        );

        // Limpiar cualquier vista previa en el layout
        lyContactos.removeAllViews();

        if (cur != null) {
            while (cur.moveToNext()) {
                // Obtener el índice de las columnas en la proyección
                int indexNombre = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

                // Extraer el nombre del contacto
                String nombre = indexNombre != -1 ? cur.getString(indexNombre) : "Sin Nombre";

                // Crear un nuevo TextView
                TextView contactoView = new TextView(this);
                contactoView.setText(nombre); // Mostrar el nombre
                contactoView.setTextColor(Color.WHITE); // Ajusta los estilos según prefieras
                contactoView.setTextSize(16);
                contactoView.setPadding(10, 10, 10, 10);

                // Agregar el TextView al LinearLayout
                lyContactos.addView(contactoView);
            }
            cur.close();
        } else {
            // Mostrar mensaje si no hay contactos encontrados
            TextView noContactos = new TextView(this);
            noContactos.setText("No se encontraron contactos");
            noContactos.setTextColor(Color.RED);
            noContactos.setTextSize(16);
            noContactos.setPadding(10, 10, 10, 10);
            lyContactos.addView(noContactos);
        }
    }


    private boolean camposVacios(){
        return edtNombre.getText().toString().isEmpty() && edtApellido.getText().toString().isEmpty();
    }

    private AlertDialog crearDialogoVistaContacto() {

        // Inicializar Variables
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_vista_contacto, null);

        // Inicializar el ImageView desde el layout inflado
        ImageView imageView = alertCustomDialog.findViewById(R.id.imageViewContacto);

        // Configurar la imagen
        imageView.setImageResource(R.drawable.img_1);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Inicializar variables
        ImageButton cancelButton = alertCustomDialog.findViewById(R.id.cancelID);

        // Crear el Diálogo
        AlertDialog dialogo = alertDialog.create();

        // Establecer fondo del diálogo transparente
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Añadir evento cuando se pulsa el icono de salir
        AlertDialog finalDialogo = dialogo;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialogo.cancel();
            }
        });

        // Devolver el dialogo creado
        return dialogo;

    }


}