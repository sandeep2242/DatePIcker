package pratham.com.datepicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class RecyclerAddapter extends RecyclerView.Adapter<RecyclerAddapter.ViewHolder>{
    List<Movie> movieList;
    Context context ;
    int cent;
    int upVotes = 0,downVotes=0;
    boolean showing = false;
    public RecyclerAddapter(List<Movie> movies) {
        this.movieList = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.movieName.setText(movieList.get(position).getTitle());
        holder.language.setText(movieList.get(position).getLanguage());
        holder.certi.setText(movieList.get(position).getCertificate());
        holder.per.setText(movieList.get(position).getRating());
        Picasso.get()
                .load(movieList.get(position).getUrls())
                .into(holder.banner);
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upVotes++;
                holder.per.setText(getPer());
                holder.votes.setText((upVotes+downVotes)+" Votes");
            }
        });
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downVotes++;
                holder.per.setText(getPer());
                holder.votes.setText((upVotes+downVotes)+" Votes");
            }
        });
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!showing) {
                    holder.longText.setVisibility(View.VISIBLE);
                    showing=true;
                    holder.details.setText("Hide Details");
                }else {
                    showing= false;
                    holder.longText.setVisibility(View.GONE);
                    holder.details.setText("Show Details");
                }
                holder.longText.setText(movieList.get(position).getDetails());
            }
        });



    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView banner,up,down ;
        TextView language,movieName,per,votes,certi,details,longText;

        public ViewHolder(View itemView) {
            super(itemView);
            View v = itemView;

            banner = v.findViewById(R.id.banner);
            up = v.findViewById(R.id.up);
            down = v.findViewById(R.id.down);
            language = v.findViewById(R.id.language);
            per = v.findViewById(R.id.percent);
            votes = v.findViewById(R.id.votes);
            certi = v.findViewById(R.id.certi);
            movieName = v.findViewById(R.id.title);
            details = v.findViewById(R.id.viewDetails);
            longText = v.findViewById(R.id.showLongtext);
        }
    }

    public String getPer(){
        String per = "";

        int total = upVotes+downVotes;

        cent = (int) (100f * upVotes / total);

        if (upVotes>downVotes){


            per= cent+" %";
        }else {

            per= "-"+cent+" %";
        }

        return per;

    }
}
