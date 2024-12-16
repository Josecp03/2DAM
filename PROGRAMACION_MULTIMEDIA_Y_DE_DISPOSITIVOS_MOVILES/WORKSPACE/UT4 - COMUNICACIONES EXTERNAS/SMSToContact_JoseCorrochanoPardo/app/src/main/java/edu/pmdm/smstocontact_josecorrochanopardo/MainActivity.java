package edu.pmdm.smstocontact_josecorrochanopardo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSION_REQUEST_SEND_SMS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Solicitar los permisos para mandar SMS
        solicitarPermisosSMS();

        // Listener para cuando se pulse el botón de seleccionar contacto
        btnSeleccionarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hacer visible los layouts para la búsqueda
                lyNombre.setVisibility(View.VISIBLE);
                lyApellido.setVisibility(View.VISIBLE);

            }
        });

        // Lisener para cuando se pulsa el botón de buscar por nombre
        imgbBuscarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarPermisoContactos();
            }
        });

        // Lisener para cuando se pulsa el botón de buscar por apellido
        imgBuscarApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarPermisoContactos();
            }
        });

    }

    private void solicitarPermisosSMS() {

        // Verificar permisos de envío de SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
        }

    }


    private void solicitarPermisoContactos() {

        // Comprobar que los permisos estén ya concedidos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // Pedir los permisos
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);

        } else {

            // Llamar al métood para realizar la búsqueda de los contactos
            realizarBusquedaContactos();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Comprobar cúal es el código del permiso
        if (requestCode == PERMISSION_REQUEST_READ_CONTACTS) {

            // Comprobar que ya se haya garantizado el permiso
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Realizar la búsqueda de los contactos
                realizarBusquedaContactos();

            } else {

                // Mostrar un mensaje indicando que se han denegado los permisos
                Toast.makeText(this, "Permiso para acceder a los contactos denegado", Toast.LENGTH_SHORT).show();

            }
        }


        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso para enviar SMS concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso para enviar SMS denegado", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void realizarBusquedaContactos() {

        // Hacer visible el layout donde van a aparecer los contactos
        lyContactos.setVisibility(View.VISIBLE);

        // Comprobar si los campos están vacíos
        if (camposVacios()) {

            // Llamar al método que muestra todos los contactos sin ningún filtro
            mostrarTodosLosContactos();

        } else {

            // Asignar a variables el nombre y apellido introducido en los editText
            String filtroNombre = edtNombre.getText().toString();
            String filtroApellido = edtApellido.getText().toString();

            // Llamar al método para mostrar los contactos filtrados por nombre y apellido
            mostrarContactosFiltrados(filtroNombre, filtroApellido);

        }
    }

    private boolean camposVacios() {
        return edtNombre.getText().toString().isEmpty() && edtApellido.getText().toString().isEmpty();
    }

    private void mostrarTodosLosContactos() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER},
                null,
                null,
                null
        );

        mostrarContactosDesdeCursor(cursor);
    }

    private void mostrarContactosFiltrados(String nombre, String apellido) {

        String filtro;
        String[] argsFiltro;

        // Convertir caracteres especiales en patrones SQL
        nombre = nombre.replace("#", "%").replace("?", "_");
        apellido = apellido.replace("#", "%").replace("?", "_");

        // Comprobar cada caso posible
        if (!nombre.isEmpty() && apellido.isEmpty()) {
            // Buscar solo por nombre
            filtro = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
            argsFiltro = new String[]{nombre + "%"};
        } else if (nombre.isEmpty() && !apellido.isEmpty()) {
            // Buscar solo por apellido
            filtro = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
            argsFiltro = new String[]{"% " + apellido + "%"};
        } else {
            // Buscar por nombre y apellido
            filtro = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ? AND " +
                    ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
            argsFiltro = new String[]{nombre + "%", "% " + apellido + "%"};
        }

        // Realizar la consulta al ContentResolver
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER},
                filtro,
                argsFiltro,
                null
        );

        mostrarContactosDesdeCursor(cursor);
    }


    private void mostrarContactosDesdeCursor(Cursor cursor) {

        // Limpiar lo que había antes
        lyContactos.removeAllViews();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String nombreCompleto = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                @SuppressLint("Range") String contactoId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") int tieneTelefono = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (tieneTelefono > 0) {
                    TextView textView = new TextView(this);
                    textView.setText(nombreCompleto);
                    textView.setTextSize(16);
                    textView.setPadding(20, 10, 20, 10);
                    textView.setTextColor(getResources().getColor(android.R.color.white));
                    lyContactos.addView(textView);

                    textView.setOnLongClickListener(v -> {
                        edtMensaje.setVisibility(View.VISIBLE);
                        btnEnviarSMS.setVisibility(View.VISIBLE);

                        btnEnviarSMS.setOnClickListener(btn -> mostrarDialogoContacto(nombreCompleto, contactoId));
                        return true;
                    });
                }
            }
            cursor.close();
        } else {
            Toast.makeText(this, "No se encontraron contactos", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDialogoContacto(String nombreCompleto, String contactoId) {

        // Obtener el número de teléfono del contacto
        String telefono = obtenerTelefonoContacto(contactoId);

        // Obtener el mensaje ingresado
        String mensaje = edtMensaje.getText().toString().trim();

        // Enviar el SMS al contacto
        enviarSMS(telefono, mensaje);

        // Comprobar que el mensaje esté vacío
        if (mensaje.isEmpty()) {
            Toast.makeText(this, "No puedes enviar un SMS sin mensaje", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inflar el diseño del diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_vista_contacto, null);

        // Configurar los datos en el diálogo
        TextView telefonoView = dialogView.findViewById(R.id.TextViewTelefono);
        TextView nombreView = dialogView.findViewById(R.id.textViewNombre);
        TextView mensajeView = dialogView.findViewById(R.id.textViewMensaje);
        ImageView fotoContacto = dialogView.findViewById(R.id.imageViewContacto);

        // Asignar los valores obtenidos al diálogo
        telefonoView.setText(telefono);
        nombreView.setText(nombreCompleto);
        mensajeView.setText(mensaje);

        // Cargar la foto del contacto
        cargarFotoContacto(contactoId, fotoContacto);

        // Crear y mostrar el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // Configurar el botón de cierre
        ImageButton closeButton = dialogView.findViewById(R.id.cancelID);
        closeButton.setOnClickListener(v -> {
            dialog.dismiss();
            reiniciarInterfaz();
        });

    }



    private String obtenerTelefonoContacto(String contactoId) {
        ContentResolver cr = getContentResolver();
        Cursor phoneCursor = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{contactoId},
                null
        );

        String telefono = "No disponible";
        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            @SuppressLint("Range") String numero = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            telefono = numero != null ? numero : telefono;
            phoneCursor.close();
        }

        return telefono;
    }


    private void cargarFotoContacto(String contactoId, ImageView imageView) {
        ContentResolver cr = getContentResolver();
        Cursor photoCursor = cr.query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Photo.PHOTO_URI},
                ContactsContract.Data.CONTACT_ID + " = ? AND " +
                        ContactsContract.Data.MIMETYPE + " = ?",
                new String[]{contactoId, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE},
                null
        );

        if (photoCursor != null && photoCursor.moveToFirst()) {
            @SuppressLint("Range") String photoUri = photoCursor.getString(photoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));
            if (photoUri != null) {
                imageView.setImageURI(Uri.parse(photoUri));
            } else {
                imageView.setImageResource(R.drawable.img_1);
            }
            photoCursor.close();
        } else {
            imageView.setImageResource(R.drawable.img_1);
        }
    }

    private void reiniciarInterfaz() {

        // Ocultar todos los elementos no iniciales
        lyNombre.setVisibility(View.INVISIBLE);
        lyApellido.setVisibility(View.INVISIBLE);
        lyContactos.setVisibility(View.INVISIBLE);
        edtMensaje.setVisibility(View.INVISIBLE);
        btnEnviarSMS.setVisibility(View.INVISIBLE);

        // Limpiar los campos de texto
        edtNombre.setText("");
        edtApellido.setText("");
        edtMensaje.setText("");
    }

    private void enviarSMS(String numero, String mensaje) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numero, null, mensaje, null, null);
            Toast.makeText(this, "Mensaje enviado a " + numero, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al enviar el mensaje: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
