package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red, 2 = empty
    int currentPlayer = 0;
    boolean isActive = true;
    int counter = 0;
    int[] CoinList = {2,2,2,2,2,2,2,2,2};
    int[][] winningList = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void againPlay(View view){
        TextView textView = (TextView) findViewById(R.id.winDrawMsg);
        Button againClick = (Button) findViewById(R.id.playAgainButton);
        textView.setVisibility(View.INVISIBLE);
        againClick.setVisibility(View.INVISIBLE);

        GridLayout myLayout = (GridLayout) findViewById(R.id.myGrid);
        //androidx.gridlayout.widget.GridLayout myLayout = findViewById(R.id.myGrid);
        for(int i = 0; i < myLayout.getChildCount(); i++) {
            ImageView image = (ImageView) myLayout.getChildAt(i);
            image.setImageDrawable(null);
        }

         currentPlayer = 0;
        isActive = true;
        counter = 0;
        for(int i = 0; i<9; i++)
            CoinList[i] = 2;
        Log.i("info", "Button Pressed");
    }

    public void dropIn(View view){
            ImageView coin = (ImageView) view;
            int currentCoin = Integer.parseInt(coin.getTag().toString());
            if (CoinList[currentCoin] == 2 && isActive) {

                coin.setTranslationY(-1500);
                CoinList[currentCoin] = currentPlayer;

                if (currentPlayer == 0) {
                    coin.setImageResource(R.drawable.yellow);
                    coin.animate().translationYBy(1500).setDuration(200);
                    currentPlayer = 1;
                } else {
                    coin.setImageResource(R.drawable.red);
                    coin.animate().translationYBy(1500).setDuration(200);
                    currentPlayer = 0;
                }

                counter++;

                for (int[] winList : winningList) {
                    if ((CoinList[winList[0]] == CoinList[winList[1]]) && (CoinList[winList[1]] == CoinList[winList[2]]) && CoinList[winList[0]] != 2) {
                        TextView textView = (TextView) findViewById(R.id.winDrawMsg);
                        Button againClick = (Button) findViewById(R.id.playAgainButton);
                        if (currentPlayer == 1)
                            textView.setText("Yellow Wins");
                        else
                            textView.setText("Red Wins");
                        isActive = false;
                        textView.setVisibility(View.VISIBLE);
                        againClick.setVisibility(View.VISIBLE);
                    }
                }
                if(counter == 9 && isActive){
                    TextView textView = (TextView) findViewById(R.id.winDrawMsg);
                    Button againClick = (Button) findViewById(R.id.playAgainButton);
                    textView.setText("Draw");
                    textView.setVisibility(View.VISIBLE);
                    againClick.setVisibility(View.VISIBLE);
                }
            }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.winDrawMsg);
        Button againClick = (Button) findViewById(R.id.playAgainButton);
        textView.setVisibility(View.INVISIBLE);
        againClick.setVisibility(View.INVISIBLE);

    }
}

