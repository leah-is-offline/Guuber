package com.example.guuber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


/**
 * Intent testing for the MapsDriverActivity.
 *  Tests are based on current app functionality.
 *  >>> USER MUST HAVE SIGNED IN  ONCE AS A DRIVER BEFORE RUNNING THESE TESTS.
 *  Robotium does not want to click the google dialog yet
 */
public class MapsDriverActivityTest {

    private Solo solo;

    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class,true,true);

    /**
     * * runs before all tests and creates solo instance.
     * YOU MUST HAVE SIGNED IN ONCE TO RUN THIS TEST
     * @throws Exception
     **/
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
        solo.waitForActivity(LoginActivity.class, 2000);
        solo.clickOnText("Sign In");
    }

    /**
     * check the driver search Button Function
     */
    @Test
    public void driverSearchButton(){
        solo.waitForActivity(MapsDriverActivity.class, 1000);
        solo.clickOnText("Search");
        solo.assertCurrentActivity("Activity should stay the same", MapsDriverActivity.class);
    }

    /**
     * check that when profile is clicked the activity is changed
     * to driver profile
     */
    @Test
    public void testProfileActivity(){
        solo.waitForActivity(MapsDriverActivity.class, 1000);
        solo.pressSpinnerItem(0,1);
        solo.assertCurrentActivity("Activity should change to profile activity", DriverProfileActivity.class);
    }

    /**
     * check that when profile is clicked the activity is changed
     * to wallett activity
     */
    @Test
    public void testWalletActivity(){
        solo.waitForActivity(MapsDriverActivity.class, 1000);
        solo.pressSpinnerItem(0,2);
        solo.assertCurrentActivity("Activity should change to wallett activity", WalletActivity.class);
    }

    /**
     * check that when profile is clicked the activity is changed
     * to Scan QR activity
     */
    @Test
    public void testScanQRActivity(){
        solo.waitForActivity(MapsDriverActivity.class, 1000);
        solo.pressSpinnerItem(0,3);
        solo.assertCurrentActivity("Activity should change to scan QR activity", ScanQrActivity.class);
    }


    /**
     * closes the activity after each test
     * @throws Exception
     * */
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
