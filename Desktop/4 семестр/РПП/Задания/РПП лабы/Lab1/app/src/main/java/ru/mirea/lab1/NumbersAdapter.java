package ru.mirea.lab1;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Locale;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>{
    private int numberItems;
    private LinearLayout BackgroundFrameLayout;
    private int whiteColor;
    private int grayColor;

    public NumbersAdapter(int numberItems) {
        this.numberItems = numberItems;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        whiteColor = ContextCompat.getColor(context, R.color.colorWhite);
        grayColor = ContextCompat.getColor(context, R.color.colorGray);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.bind(position + 1);
        }
        BackgroundFrameLayout = (LinearLayout)holder.layoutL;
        if (position % 2 == 1) {
            BackgroundFrameLayout.setBackgroundColor(grayColor);
        } else {BackgroundFrameLayout.setBackgroundColor(whiteColor);}
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        ImageView viewImage;
        TextView viewText;
        LinearLayout layoutL;
        RuleBasedNumberFormat nf;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            viewImage = itemView.findViewById(R.id.ivImage);
            viewText = itemView.findViewById(R.id.item_txt);
            layoutL = itemView.findViewById(R.id.ll_item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                        RuleBasedNumberFormat.SPELLOUT);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void bind(int listIndex){
            viewText.setText(nf.format(listIndex));
        }
    }
}
