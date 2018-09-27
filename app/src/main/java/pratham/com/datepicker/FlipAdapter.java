package pratham.com.datepicker;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


class FlipAdapter extends RecyclerView.Adapter<FlipAdapter.ViewHolder> {
    static ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    List<Flip_Get_Set> get_sets;
    ViewGroup context2;
    Context context;
    View v1, v2, v4;
    private boolean isSelected = false;

    public FlipAdapter(List<Flip_Get_Set> get_sets, ArrayList<Integer> array, View v2) {
        this.get_sets = get_sets;
        this.id = array;
        this.v4 = v2;
    }


    @Override
    public int getItemViewType(int position) {
        if (get_sets.get(position).getId() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public FlipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context2 = parent;
        context = parent.getContext();
        Log.d("sand","ViewHolder is running");

        if (viewType == 1) {
            v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.flip_single_row, parent, false);
            return new ViewHolder(v1);
        } else {
            v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.between, parent, false);
            return new ViewHolder(v2);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull FlipAdapter.ViewHolder holder, final int position) {


        if (holder.v == v1) {
            if (id != null) {
                for (int i = 0; i < id.size(); i++) {
                    if (id.get(i) == get_sets.get(position).getId()) {
                        addView(holder, position);
                        holder.rel.setVisibility(View.VISIBLE);
                        holder.like.setVisibility(View.GONE);
                    }
                }

            } else {
                addView(holder, position);
                holder.rel.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {

        return get_sets.size();
    }

    void addView(FlipAdapter.ViewHolder holder, final int position) {


        holder.productName.setText(get_sets.get(position).getName());
        holder.peroff.setText(get_sets.get(position).getOff() + " % off");

        holder.oldPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(get_sets.get(position).getPrice()));
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.rating.setText(get_sets.get(position).getRating());
        holder.votes.setText("( " + NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(get_sets.get(position).getVotes())) + " )");
        Picasso.get()
                .load(get_sets.get(position).getImgUrl())
                .into(holder.productImage);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!get_sets.get(position).isHeart()) {
                    Picasso.get()
                            .load(R.drawable.heart)
                            .into((ImageView) v);

                    if (ids.size() > 0) {

                        if (!ids.contains(get_sets.get(position).getId())) {
                            ids.add(get_sets.get(position).getId());
                        }
                    } else {
                        ids.add(get_sets.get(position).getId());
                    }
                    Toast.makeText(context, "Item added to your wishlist", Toast.LENGTH_SHORT).show();
                    //isSelected = true;
                    get_sets.get(position).setHeart(true);


                } else {

                    Picasso.get()
                            .load(R.drawable.ic_action_heartg)
                            .into((ImageView) v);
                    if (ids.size() > 0) {


                        for (int i = 0; i < ids.size(); i++) {
                            if (get_sets.get(position).getId() == ids.get(i)) {
                                ids.remove(i);
                                Toast.makeText(context, "Item has been removed from your wishlist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    get_sets.get(position).setHeart(false);
                }


            }
        });

        int discount = get_sets.get(position).getPrice() * (100 - get_sets.get(position).getOff()) / 100;
        holder.newPrice.setText(context.getResources().getString(R.string.Rs) + String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(discount)));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, like;
        TextView productName, rating, votes, newPrice, oldPrice, peroff;
        RelativeLayout rel;
        View v;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;

            productImage = v.findViewById(R.id.flip_img);
            like = v.findViewById(R.id.like);
            productName = v.findViewById(R.id.flip_itemName);
            rating = v.findViewById(R.id.flip_rating);
            votes = v.findViewById(R.id.flip_productVotes);
            newPrice = v.findViewById(R.id.flip_newPrice);
            oldPrice = v.findViewById(R.id.flip_orPrice);
            peroff = v.findViewById(R.id.flip_discountPer);
            rel = v.findViewById(R.id.relLayout);

        }
    }

}
