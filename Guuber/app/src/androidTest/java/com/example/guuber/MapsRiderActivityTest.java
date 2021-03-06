package com.example.guuber;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Intent testing for the MapsRIDERActivity.
 *  Tests are based on current app functionality.
 *  >>> USER MUST HAVE SIGNED IN  ONCE AS A RIDER BEFORE RUNNING THESE TESTS.
 */
public class MapsRiderActivityTest {

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
     * check that when profile is clicked the activity is changed
     * to rider  profile
     */
    @Test
    public void testProfileActivity(){
        solo.waitForActivity(MapsRiderActivity.class, 1000);
        solo.pressSpinnerItem(0,1);
        solo.assertCurrentActivity("Activity should change to profile activity", RiderProfileActivity.class);
    }


    /**
     * check the rider make request button
     * before providing coordinates
     */
    @Test
    public void testSignoutActivity(){
        solo.waitForActivity(MapsRiderActivity.class, 1000);
        solo.pressSpinnerItem(0,3);
        solo.assertCurrentActivity("Activity should change to log in activity", LoginActivity.class);
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
