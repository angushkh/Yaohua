package yaohua.com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yaohua.com.Images.Image2;
import yaohua.com.R;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    private  static final String TAG = "RecyclerAdapter2";

    private Context scontext;
    private Context scontext2;
    private Context scontext3;
    private ArrayList<Image2> image2List;

    public RecyclerAdapter2(Context context1, Context context2, Context context3, ArrayList<Image2> image2List) {
        this.scontext = context1;
        this.scontext2 = context2;
        this.scontext3 = context3;
        this.image2List = image2List;
    }

    @NonNull
    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitle2.setText(image2List.get(position).getTitle2());
        holder.tvDetail2.setText(image2List.get(position).getDetail2());
        holder.tvSells.setText(image2List.get(position).getSells());

        Picasso.get().load(image2List.get(position).getUrl2()).into(holder.imageView2);
    }

    @Override
    public int getItemCount() {
        return image2List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView2;
        TextView tvTitle2;
        TextView tvDetail2;
        TextView tvSells;

        public ViewHolder(View itemView) {

            super(itemView);

            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            tvTitle2 = (TextView) itemView.findViewById(R.id.tvTitle2);
            tvDetail2 = (TextView) itemView.findViewById(R.id.tvDetail2);
            tvSells = (TextView) itemView.findViewById(R.id.tvSell);
        }
    }
}
