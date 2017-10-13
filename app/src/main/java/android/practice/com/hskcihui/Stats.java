package android.practice.com.hskcihui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.widget.TextView;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        DatabaseController dbController;
        dbController = new DatabaseController(getBaseContext());

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayoutStats);

        int[][] stats;
        stats = dbController.getStats();

        String[][] statsGridContent = {{" ","0","1","2","3","4"},
                {"HSK 1",Integer.toString(stats[0][0]),Integer.toString(stats[1][0]),Integer.toString(stats[2][0]),
                        Integer.toString(stats[3][0]),Integer.toString(stats[4][0])},
                {"HSK 2",Integer.toString(stats[0][1]),Integer.toString(stats[1][1]),Integer.toString(stats[2][1]),
                        Integer.toString(stats[3][1]),Integer.toString(stats[4][1])},
                {"HSK 3",Integer.toString(stats[0][2]),Integer.toString(stats[1][2]),Integer.toString(stats[2][2]),
                        Integer.toString(stats[3][2]),Integer.toString(stats[4][2])},
                {"HSK 4",Integer.toString(stats[0][3]),Integer.toString(stats[1][3]),Integer.toString(stats[2][3]),
                        Integer.toString(stats[3][3]),Integer.toString(stats[4][3])},
                {"HSK 5",Integer.toString(stats[0][4]),Integer.toString(stats[1][4]),Integer.toString(stats[2][4]),
                        Integer.toString(stats[3][4]),Integer.toString(stats[4][4])},
                {"HSK 6",Integer.toString(stats[0][5]),Integer.toString(stats[1][5]),Integer.toString(stats[2][5]),
                        Integer.toString(stats[3][5]),Integer.toString(stats[4][5])}};

        for (int x=0; x<=6; x++){
            for (int y=0; y<=5; y++){
                TextView textView = new TextView(this);
                if (x%2 != 0)
                    textView.setBackgroundColor(Color.DKGRAY);
                if (y==2)
                    textView.setTextColor(getResources().getColor(R.color.red));
                if (y==3)
                    textView.setTextColor(getResources().getColor(R.color.yellow));
                if (y==4)
                    textView.setTextColor(getResources().getColor(R.color.green));
                if (y==5)
                    textView.setTextColor(getResources().getColor(R.color.cyan));
                textView.setHeight(50);
                textView.setWidth(100);
                textView.setGravity(Gravity.CENTER);
                textView.setText(statsGridContent[x][y]);
                gridLayout.addView(textView);
            }
        }
    }
}
