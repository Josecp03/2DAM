package edu.pmdm.prc1_josecorrochano;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity is the entry point of the application. It manages the user interface and the logic for a
 * workout timer application, including work and rest timers with user-defined parameters.
 */
public class MainActivity extends AppCompatActivity {

    // UI and Class variables
    EditText edtWork;
    EditText edtRest;
    EditText edtSets;
    ImageButton ibPlay;
    TextView txtTime;
    TextView txtAction;
    TextView txtSeriesLeft;
    TextView txtTitleSeriesLeft;
    CountDownTimer workTimer;
    CountDownTimer restTimer;
    ConstraintLayout constraintLayout;
    int remainingSets;

    /**
     * Called when the activity is first created. This is where you should perform one-time
     * initialization such as setting up the layout and initializing views.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data it most recently supplied in
     *                           {@link #onSaveInstanceState(Bundle)}. Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // View mapping
        edtWork = findViewById(R.id.edtWork);
        edtRest = findViewById(R.id.edtRest);
        edtSets = findViewById(R.id.edtSets);
        ibPlay = findViewById(R.id.ibPlay);
        txtTime = findViewById(R.id.txtTime);
        txtAction = findViewById(R.id.txtAction);
        txtSeriesLeft = findViewById(R.id.txtSeriesLeft);
        txtTitleSeriesLeft = findViewById(R.id.txtTitleSeriesLeft);
        constraintLayout = findViewById(R.id.constraintLayout);

        /**
         * Sets up an onClick listener for the ibPlay button. When the button is pressed,
         * the method performs the following actions:
         * <ul>
         *     <li>Checks the input values of the EditText fields by calling {@link #checkEdt()}.</li>
         *     <li>If the input values are incorrect, calls {@link #showAlert()} to display an alert.</li>
         *     <li>If the input values are valid, disables the button to prevent multiple clicks
         *         that may cause counter overlap.</li>
         *     <li>Retrieves the work time from the edtWork field and converts it to a long value
         *         which is passed to the {@link #startWorkTimer(long)} method.</li>
         *     <li>Retrieves the remaining sets from the edtSets field and assigns it to the
         *         remainingSets variable.</li>
         *     <li>Updates the txtSeriesLeft TextView to display the number of remaining sets.</li>
         *     <li>Starts the work timer using the retrieved work time value.</li>
         * </ul>
         */
        ibPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Check the value of the editTextView
                if (checkEdt()) {
                    showAlert(); // Method to show an alert when edt values are incorrect
                } else {

                    // Disable the button to not chain counters
                    ibPlay.setEnabled(false);

                    // Assign the work time value of type long to the counter method
                    long timeWork = Long.parseLong(edtWork.getText().toString());

                    // Assign the remaining sets to a variable
                    remainingSets = Integer.parseInt(edtSets.getText().toString());

                    // Set the corresponding remaining series in the txtSeriesLeft
                    txtSeriesLeft.setText(String.valueOf(remainingSets));

                    // Launch de work timer
                    startWorkTimer(timeWork);

                }
            }

        });

    }

    /**
     * Displays an alert dialog when input values are invalid.
     */
    private void showAlert() {

        // Create a constructor for an AlertDialog associated with the current activity
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        // Set the message to be displayed in the dialog
        alert.setMessage("Invalid Input")

                // Set the positive button and its behavior when clicked
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Close the alert when you press the button
                        dialog.dismiss();

                    }

                });

        // Create the alert thanks to the previous constructor
        AlertDialog title = alert.create();

        // Set the title of the dialog box
        title.setTitle("Error");

        // Display the dialog box on the screen
        title.show();

    }

    /**
     * Starts the work timer countdown.
     *
     * @param timeWork The duration of the work phase in seconds.
     */
    private void startWorkTimer(long timeWork) {

        // Set the gradient green background color
        constraintLayout.setBackgroundResource(R.drawable.degradado_verde);

        // Startup sound
        playBeep();

        // Change colors when the work timer is running
        changeColorGreen();

        // Initialize a new CountDownTimer for the work session.
        workTimer = new CountDownTimer(timeWork * 1000, 1000) {

            // This method is called every second (1000 milliseconds) until the timer finishes.
            @Override
            public void onTick(long millisUntilFinished) {

                // Update the displayed time left for the work session in seconds.
                txtTime.setText(String.valueOf(millisUntilFinished / 1000));

                // Set the action text to indicate that the current phase is "WORK".
                txtAction.setText("WORK");

            }

            // This method is called when the timer finishes.
            @Override
            public void onFinish() {

                // Decrease the count of remaining sets by one.
                remainingSets--;

                // Update the displayed number of series left.
                txtSeriesLeft.setText(String.valueOf(remainingSets));

                // Check if there are more sets remaining.
                if (remainingSets > 0) {

                    // Parse the rest time from the input field and start the rest timer.
                    long timeRest = Long.parseLong(edtRest.getText().toString());
                    startRestTimer(timeRest);

                } else {

                    // Change the background color to indicate completion.
                    constraintLayout.setBackgroundColor(Color.parseColor("#1C1C1C"));

                    // Update the action text to indicate that the work session has finished.
                    txtAction.setText("FINISHED!");

                    // Play a gong sound to signal the end.
                    playGong();

                    // Change the initial color, possibly resetting UI elements.
                    changeInitColor();

                    // Enable the play button for further actions.
                    ibPlay.setEnabled(true);

                }
            }
        }.start(); // Start the countdown timer.

    }

    /**
     * Starts the rest timer countdown.
     *
     * @param timeRest The duration of the rest phase in seconds.
     */
    private void startRestTimer(long timeRest) {

        // Set the background resource of the layout to a red gradient to indicate rest mode.
        constraintLayout.setBackgroundResource(R.drawable.degradado_rojo);

        // Change the UI color to red, potentially highlighting the rest phase.
        changeColorRed();

        // Play a beep sound to signal the start of the rest period.
        playBeep();

        // Initialize a new CountDownTimer for the rest duration.
        restTimer = new CountDownTimer(timeRest * 1000, 1000) {

            // This method is called every second (1000 milliseconds) until the timer finishes.
            @Override
            public void onTick(long millisUntilFinished) {

                // Update the displayed time left for the rest period in seconds.
                txtTime.setText(String.valueOf(millisUntilFinished / 1000));

                // Set the action text to indicate that the current phase is "REST".
                txtAction.setText("REST");

            }

            // This method is called when the timer finishes.
            @Override
            public void onFinish() {

                // Start the work timer again with the duration specified in the input field.
                startWorkTimer(Long.parseLong(edtWork.getText().toString()));

            }

        }.start(); // Start the countdown timer.

    }

    /**
     * Plays a beep sound to signal the start of a timer phase.
     */
    public void playBeep() {

        // Create a MediaPlayer instance with the beep sound resource.
        MediaPlayer mpBeep = MediaPlayer.create(getApplicationContext(), R.raw.beep);

        // Start playing the beep sound.
        mpBeep.start();

    }

    /**
     * Plays a gong sound to signal the end of the timer phases.
     */
    public void playGong() {

        // Create a MediaPlayer instance with the gong sound resource.
        MediaPlayer mpGong = MediaPlayer.create(getApplicationContext(), R.raw.gong);

        // Start playing the gong sound.
        mpGong.start();

    }

    /**
     * Checks if the user input in the EditText fields is valid.
     *
     * @return true if any of the fields are empty or contain the value "0"; false otherwise.
     */
    public boolean checkEdt() {

        // Retrieve the text input from the sets, work, and rest EditText fields.
        String sets = edtSets.getText().toString();
        String work = edtWork.getText().toString();
        String rest = edtRest.getText().toString();

        // Return true if any of the fields are empty or contain the value "0".
        return sets.isEmpty() || sets.equals("0") || work.isEmpty() || work.equals("0") || rest.isEmpty() || rest.equals("0");

    }

    /**
     * Changes the text color of the input fields and labels to white.
     */
    public void changeInitColor() {

        // Retrieve the color resource for white.
        int white = getResources().getColor(R.color.white);

        // Set the text color of the input fields and labels to white.
        edtSets.setTextColor(white);
        edtWork.setTextColor(white);
        edtRest.setTextColor(white);
        txtAction.setTextColor(white);
        txtTime.setTextColor(white);
        txtSeriesLeft.setTextColor(white);
        txtTitleSeriesLeft.setTextColor(white);

    }

    /**
     * Changes the text color of the input fields and labels to red.
     */
    public void changeColorRed() {

        // Retrieve the color resource for blackRed.
        int blackRed = getResources().getColor(R.color.blackRed);

        // Set the text color of the input fields and labels to blackRed.
        edtSets.setTextColor(blackRed);
        edtWork.setTextColor(blackRed);
        edtRest.setTextColor(blackRed);
        txtAction.setTextColor(blackRed);
        txtTime.setTextColor(blackRed);
        txtSeriesLeft.setTextColor(blackRed);
        txtTitleSeriesLeft.setTextColor(blackRed);

    }

    /**
     * Changes the text color of the input fields and labels to green.
     */
    public void changeColorGreen() {

        // Retrieve the color resource for blackGreen.
        int blackGreen = getResources().getColor(R.color.blackGreen);

        // Set the text color of the input fields and labels to blackGreen.
        edtSets.setTextColor(blackGreen);
        edtWork.setTextColor(blackGreen);
        edtRest.setTextColor(blackGreen);
        txtAction.setTextColor(blackGreen);
        txtTime.setTextColor(blackGreen);
        txtSeriesLeft.setTextColor(blackGreen);
        txtTitleSeriesLeft.setTextColor(blackGreen);

    }

}
