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

import yaohua.com.Images.Image1;
import yaohua.com.R;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {

    private  static final String TAG = "RecyclerAdapter1";

    private Context mcontext;
    private Context ycontext;
    private Context bcontext;
    private ArrayList<Image1> image1List;

    public RecyclerAdapter1(Context context1, Context context2, Context context3, ArrayList<Image1> image1List) {
        this.mcontext = context1;
        this.ycontext = context2;
        this.bcontext = context3;
        this.image1List = image1List;
    }

    @NonNull
    @Override
    public RecyclerAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item1, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitle.setText(image1List.get(position).getTitle());
        holder.tvDetail.setText(image1List.get(position).getDetail());
        holder.tvPrice.setText(image1List.get(position).getPrice());

        Picasso.get().load(image1List.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return image1List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;
        TextView tvDetail;
        TextView tvPrice;

        public ViewHolder(View itemView) {

            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView1);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle1);
            tvDetail = (TextView) itemView.findViewById(R.id.tvDetail1);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice1);
        }
    }
}
