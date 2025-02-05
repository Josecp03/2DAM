package edu.pmdm.files_josecorrochano.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.pmdm.files_josecorrochano.R;

public class FileLineAdapter extends RecyclerView.Adapter<FileLineAdapter.ViewHolder> {

    // Atributos
    private List<String> lines;
    private OnItemLongClickListener onItemLongClickListener;
    private TextView textViewCountWords;

    // Interfaz para el longClick
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    // Constructor
    public FileLineAdapter(List<String> lines, OnItemLongClickListener longClickListener, TextView textViewCountWords) {
        this.lines = lines;
        this.onItemLongClickListener = longClickListener;
        this.textViewCountWords = textViewCountWords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Establecer el texto del TextView con la línea correspondiente de la lista
        holder.textView.setText(lines.get(position));

        // Reiniciar el color de fondo p
        holder.itemView.setBackgroundColor(Color.TRANSPARENT);

        // LongClick sobre el itemView
        holder.itemView.setOnLongClickListener(v -> {
            onItemLongClickListener.onItemLongClick(position);
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    // Eliminar una línea
    public void removeItem(int position) {

        // Eliminar la línea de la lista
        lines.remove(position);

        // Notificar el cambio
        notifyItemRemoved(position);

        // Actualizar la lista
        notifyItemRangeChanged(position, lines.size());

        // Actualizar el número de palabras
        textViewCountWords.setText("Words: " + calculateWordCount());

    }

    // Añadir una nueva línea
    public void addItem(String newLine) {

        // Añadir la línea a la lista
        lines.add(newLine);

        // Notidicar el cambio
        notifyItemInserted(lines.size() - 1);

        // Actualizar el número de palabras
        textViewCountWords.setText("Words: " + calculateWordCount());
    }

    // Calcular el número total de palabras
    private int calculateWordCount() {

        // Inicializar el contador
        int count = 0;

        // Recorrer la lista de líneas
        for (String line : lines) {

            // Aumentar el contador por cada elemento separado por un espacio
            count += line.split("\\s+").length;

        }

        // Devolver el contandor
        return count;

    }

    // Obtener las líneas
    public List<String> getLines() {
        return lines;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Declarar el textView
        TextView textView;

        // Inicializar el textView
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewLine);
        }

    }
}
