package ie.ul.dungeonfinder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class CalendarFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private TextView dateText;

/*    protected void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    } */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        dateText = v.findViewById(R.id.date_text);
        
        //When the "Show Dialog" button is clicked, the Date Picker Dialog (the calender pop up) is shown
        v.findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    //pretty much gets the date chosen from the calender
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //outputs the date that was clicked by the user onto the calender screen
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }
}
