package com.example.prc3_joscorrochano.bikes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BikesContent {

    // List of all the bikes to be listed in the RecyclerView
    public static List<Bike> ITEMS = new ArrayList<>();
    public static String selectedDate = "No date selected";

    public static void loadBikesFromJSON(Context c) {
        if (!ITEMS.isEmpty()) {
            return; // Evitar cargar los datos nuevamente si ya están cargados
        }

        String json = null;
        try {
            // Leer archivo JSON
            InputStream is = c.getAssets().open("bikeList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            // Parsear JSON principal
            JSONObject jsonObject = new JSONObject(json);
            JSONArray bikeList = jsonObject.getJSONArray("bike_list");

            for (int i = 0; i < bikeList.length(); i++) {
                JSONObject jsonBike = bikeList.getJSONObject(i);

                // Validar campos obligatorios
                if (jsonBike.has("owner") && jsonBike.has("description") &&
                        jsonBike.has("city") && jsonBike.has("location") &&
                        jsonBike.has("email") && jsonBike.has("image")) {

                    String owner = jsonBike.getString("owner");
                    String description = jsonBike.getString("description");
                    String city = jsonBike.getString("city");
                    String location = jsonBike.getString("location");
                    String email = jsonBike.getString("email");
                    String image = jsonBike.getString("image");

                    // Validar que los valores no sean nulos o vacíos
                    if (!owner.isEmpty() && !description.isEmpty() &&
                            !city.isEmpty() && !location.isEmpty() &&
                            !email.isEmpty() && !image.isEmpty()) {

                        Bitmap photo = null;
                        try {
                            photo = BitmapFactory.decodeStream(c.getAssets().open("images/" + image));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Agregar el objeto válido a la lista
                        ITEMS.add(new Bike(photo, owner, description, city, location, email));
                    }
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }


    // Getters y Setters para la fecha seleccionada
    public static String getSelectedDate() {

        // Comprobar que sea nula o que esté vacía
        if (selectedDate == null || selectedDate.isEmpty()) {
            return "No date selected";
        }

        return selectedDate;

    }

    // Establecer fecha seleccionada
    public static void setSelectedDate(String selectedDate) {
        BikesContent.selectedDate = selectedDate;
    }

    public static class Bike {

        private Bitmap photo;
        private String owner;
        private String description;
        private String city;
        private String location;
        private String email;

        public Bike(Bitmap photo, String owner, String description, String city, String location, String email) {
            this.photo = photo;
            this.owner = owner;
            this.description = description;
            this.city = city;
            this.location = location;
            this.email = email;
        }

        public Bitmap getPhoto() {
            return photo;
        }

        public String getOwner() {
            return owner;
        }

        public String getDescription() {
            return description;
        }

        public String getCity() {
            return city;
        }

        public String getLocation() {
            return location;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return owner + " " + description;
        }
    }
}
