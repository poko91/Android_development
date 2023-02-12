package course.labs.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity {


    private SeekBar changeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.art);

        final LinearLayout color1 = findViewById(R.id.color1);
        final LinearLayout color2 = findViewById(R.id.color2);
        final LinearLayout color3 = findViewById(R.id.color3);
        final LinearLayout color4 = findViewById(R.id.color4);
        final LinearLayout color5 = findViewById(R.id.color5);
        final LinearLayout color6 = findViewById(R.id.color6);

        // get int values of all colors
        int color1_int = ((ColorDrawable) color1.getBackground()).getColor();
        int color2_int = ((ColorDrawable) color2.getBackground()).getColor();
        int color3_int = ((ColorDrawable) color3.getBackground()).getColor();
        int color4_int = ((ColorDrawable) color4.getBackground()).getColor();
        int color5_int = ((ColorDrawable) color5.getBackground()).getColor();
        int color6_int = ((ColorDrawable) color6.getBackground()).getColor();

        changeColor = (SeekBar) findViewById(R.id.changeColor);
        changeColor.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            int lastProgress = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor(color1, color1_int, progress);
                updateColor(color2, color2_int, progress);
                updateColor(color3, color3_int, progress);
                updateColor(color4, color4_int, progress);
                updateColor(color5, color5_int, progress);
                updateColor(color6, color6_int, progress);

                lastProgress = progress;
            }

            private void updateColor(LinearLayout color, int color_int, int progress) {
//                int color_int = ((ColorDrawable) color.getBackground()).getColor();
                float[] hsvColor = new float[3];
                Color.colorToHSV(color_int, hsvColor);
                if (lastProgress > progress) {
                    hsvColor[0] = hsvColor[0] - progress;
                } else {
                    hsvColor[0] = hsvColor[0] + progress;
                }
                color.setBackgroundColor(Color.HSVToColor(hsvColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item when clicked
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.more_info) {
            showDialogBox();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogBox() {
        View dialogBox = this.getLayoutInflater().inflate(R.layout.dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogBox).create();

        Button notNow = (Button) dialogBox.findViewById(R.id.not_now);
        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

        Button visit_MOMA = (Button) dialogBox.findViewById(R.id.visit_MOMA);
        visit_MOMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.moma.org/m#home"));
                startActivity(intent);
            }
        });
        dialog.show();
    }
}