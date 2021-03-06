package ADD;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.minigames.GameFirstExrActivity;
import com.example.minigames.MenuActivity;
import com.example.minigames.ScoresActivity;

public class CustomDialogFragment extends DialogFragment {

   private Boolean contunie=false;
   private Boolean toScores=false;
   private Boolean toMenu=false;


    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final String name = getArguments().getString("name");
final String type_game=getArguments().getString("type_game");
        final int scores = getArguments().getInt("scores");
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Ви програли\n ваш результат "+name+":- "+scores)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("\nВи бажаєте переглянути таблицю "+"\n або продовжити"+ "?")
                .setPositiveButton("Переглянути минулі результати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toScores=true;
                        Intent intent = new Intent(getActivity(), ScoresActivity.class);
                        intent.putExtra("name",name);
                                intent.putExtra("scores",scores);
                                intent.putExtra("activity",1);
                                intent.putExtra("type_game",type_game);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Продовжити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                     if(type_game=="scores_space_war") {
                         intent = new Intent(getActivity(), GameFirstExrActivity.class);
                         startActivity(intent);

                     }
                     if (type_game=="scores_adventure_world") {
                          intent = new Intent(getActivity(), GameFirstExrActivity.class);
                         startActivity(intent);
                        }

                        contunie=true;
                    }
                })
                .setNeutralButton("До меню",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), MenuActivity.class);
                        startActivity(intent);
                        toMenu=true;
                    }
                })
                .create();
    }
    public Boolean getToMenu() {
        return toMenu;
    }

    public void setToMenu(Boolean toMenu) {
        this.toMenu = toMenu;
    }

    public Boolean getContunie() {
        return contunie;
    }

    public void setContunie(Boolean contunie) {
        this.contunie = contunie;
    }

    public Boolean getToScores() {
        return toScores;
    }

    public void setToScores(Boolean toScores) {
        this.toScores = toScores;
    }
}
