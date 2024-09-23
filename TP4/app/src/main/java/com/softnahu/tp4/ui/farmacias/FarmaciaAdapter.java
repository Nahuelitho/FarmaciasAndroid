package com.softnahu.tp4.ui.farmacias;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.softnahu.tp4.R;
import com.softnahu.tp4.model.Farmacia;
import java.util.ArrayList;

public class FarmaciaAdapter extends RecyclerView.Adapter<FarmaciaAdapter.ViewHolderFarmacia> {
    private ArrayList<Farmacia> listaDeFarmacias;
    private LayoutInflater inflater;

    public FarmaciaAdapter(ArrayList<Farmacia> listaDeFarmacias, LayoutInflater inflater){
        this.listaDeFarmacias = listaDeFarmacias;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolderFarmacia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tarjeta, parent, false);
        return new ViewHolderFarmacia(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFarmacia holder, int position) {
        Farmacia farmacia = listaDeFarmacias.get(position);
        holder.tvNombre.setText(farmacia.getNombre());
        holder.tvDireccion.setText(farmacia.getDireccion());
        holder.tvInfo.setText(farmacia.getInfo());
        holder.ivFoto.setImageResource(farmacia.getFoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("farmacia",farmacia);
                Navigation.findNavController(view).navigate(R.id.detalleFarmacia2, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeFarmacias.size();
    }


    public class ViewHolderFarmacia extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvDireccion, tvInfo;
        private ImageView ivFoto;
        public ViewHolderFarmacia(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }
    }
}
