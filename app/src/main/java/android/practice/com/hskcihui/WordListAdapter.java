package android.practice.com.hskcihui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class WordListAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<Words> word;

    public WordListAdapter(Context context, int textViewResourseId, ArrayList objects){
        super(context, textViewResourseId, objects);

        this.context = context;
        word = objects;
    }

    private class ViewHolder{
        TextView wordHsk;
        TextView wordSimplified;
        TextView wordTraditional;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.activity_word_list_item, null);

            holder = new ViewHolder();
            holder.wordHsk = (TextView) convertView.findViewById(R.id.textViewListItemHsk);
            holder.wordSimplified = (TextView) convertView.findViewById(R.id.textViewListItemSimplified);
            holder.wordTraditional = (TextView) convertView.findViewById(R.id.textViewListItemTraditional);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Words individualWord = word.get(position);
        holder.wordHsk.setText(individualWord.getHsk());
        holder.wordSimplified.setText(individualWord.getSimplified());
        holder.wordTraditional.setText(individualWord.getTraditional());
        return convertView;
    }
}
