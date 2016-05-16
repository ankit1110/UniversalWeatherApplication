package ohmsoftwaresinc.com.universalweatherapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import ohmsoftwaresinc.com.universalweatherapplication.Model.Example;
import ohmsoftwaresinc.com.universalweatherapplication.Model.List;
import ohmsoftwaresinc.com.universalweatherapplication.Model.Weather;
import ohmsoftwaresinc.com.universalweatherapplication.R;

/**
 * Created by kalpesh on 21/09/2015.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    /**
     * Created by Trey Robinson on 8/3/14.
     * Copyright 2014 MindMine LLC.
     */

        private Example product;
        private int rowLayout;
        private Context mContext;

        public ProductsAdapter(Example product2, int rowLayout, Context context) {
            this.product = product2;
            this.rowLayout = rowLayout;
            this.mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int j) {

            ohmsoftwaresinc.com.universalweatherapplication.Model.List curr_date = product.getList().get(j);
            viewHolder.today_date.setText(""+curr_date.getDtTxt().toString());

            viewHolder.temp_min.setText(""+curr_date.getMain().getTempMin().toString());

            viewHolder.temp_max.setText(""+curr_date.getMain().getTempMax().toString());
            viewHolder.pressure.setText(""+curr_date.getMain().getPressure().toString());
            viewHolder.humidity.setText(""+curr_date.getMain().getHumidity().toString());
          //  viewHolder.description.setText(""+curr_date.getWeather().get(j).getDescription().toString());


        }

        @Override
        public int getItemCount() {
            return product == null ? 0 : product.getList().size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView today_date,temp_min,temp_max,pressure,humidity,description;


            public ViewHolder(View itemView) {
                super(itemView);
                today_date = (TextView) itemView.findViewById(R.id.today_date);
                temp_min = (TextView) itemView.findViewById(R.id.temp_min);
                temp_max = (TextView) itemView.findViewById(R.id.temp_max);
                pressure = (TextView) itemView.findViewById(R.id.pressure);
                humidity = (TextView) itemView.findViewById(R.id.humidity);
                description = (TextView) itemView.findViewById(R.id.description);

         /*
                itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), "OnClick Version :" + getPosition(),
                            Toast.LENGTH_SHORT).show();
                    Log.i("RecyclerView", "Clicked");

                }
                });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(v.getContext(), "OnLongClick Version :" + versionName,
                            Toast.LENGTH_SHORT).show();
                    Log.i("RecyclerView", "OnLongClick");
                    return true;

                }
            });
*/
            }

        }
    }

