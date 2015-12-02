package com.cschefs.dontstarve;


import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import com.cschefs.dontstarve.MainActivity;
import com.cschefs.dontstarve.R;

/**
 * Created by Andrew on 11/27/15.
 *
 * This is an activity unit test on MainActivity
 * Purpose: To test if correct intents are being sent after a button is pressed on Main Activity
 */


public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    Button launchFindBtn;
    Intent mainLaunchIntent;

    public MainActivityTest() {
    super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainLaunchIntent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
        startActivity(mainLaunchIntent, null, null);
        launchFindBtn =
                (Button) getActivity().findViewById(R.id.find_button);

        SearchableActivityWasLaunchedWithIntentTest();
    }
    @MediumTest
    public void SearchableActivityWasLaunchedWithIntentTest(){
        launchFindBtn.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());

        final String payload = launchIntent.getStringExtra(RecipeActivity.EXTRAS_PAYLOAD_KEY);
        assertEquals("Payload is empty", MainActivity.STRING_PAYLOAD, payload);
    }
}
