package ie.ul.dungeonfinder;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {    //this is what gets created when main activity is first told to run
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Fragment selectedFragment = new ProfileFragment();   // this sets the starting fragment at the profile
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);   // this is bringing up the bottom navigation bar
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =  //this is the event lisner for the bottom navigation bar
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {  //switch case, this is how it knows which fragment to pull up when the icon on the botttom nav is tapped
                        case R.id.nav_map:
                            selectedFragment = new MapFragment();   //pulls up a map 
                            break;
                            
                            case R.id.nav_profile:
                            selectedFragment = new ProfileFragment(); //shows the profile
                            break;

                            case R.id.nav_calendar:
                            selectedFragment = new CalendarFragment(); //pulls up the calendar
                            break;
                      
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  // this is where the fragments get switched around
                            selectedFragment).commit();
                    return true;
                }
            };
}
