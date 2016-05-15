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


 //           ohmsoftwaresinc.com.universalweatherapplication.Model.List data = product.getList().get(i);
  //          Log.d("Pressure",data.getMain().getPressure().toString());
   //         Log.d("Pressure",data.getDtTxt().toString());

      //      viewHolder.countryName.setText(""+data.getDtTxt().toString());
            for(int i=0;i< product.getList().size();i++)
            {
                ohmsoftwaresinc.com.universalweatherapplication.Model.List data = product.getList().get(i);
              //  Log.d("Pressure",data.getMain().getPressure().toString());
               // Log.d("Pressure",data.getDtTxt().toString());


                viewHolder.countryName.setText(""+data.getDtTxt().toString());

                // Toast.makeText(getApplicationContext(),data.getSys().getPod().toString(),Toast.LENGTH_SHORT);

            }



           // viewHolder.countryName.setText(""+product.getCity().getCountry().toString());
            // viewHolder.versionName=viewHolder.countryName.getText().toString();


        }

        @Override
        public int getItemCount() {
            return product == null ? 0 : product.getList().size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView countryName;
            public ImageView countryImage;
            public String versionName;

            public ViewHolder(View itemView) {
                super(itemView);
                countryName = (TextView) itemView.findViewById(R.id.name);


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

