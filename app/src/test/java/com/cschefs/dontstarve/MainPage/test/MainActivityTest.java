package com.cschefs.dontstarve.MainPage.test;




import org.junit.Test;
import static org.junit.Assert.*;
import android.content.ClipData;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import com.cschefs.dontstarve.MainActivity;
import com.cschefs.dontstarve.R;
import com.cschefs.dontstarve.RecipeActivity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 11/27/15.
 * <p/>
 * This is an activity unit test on MainActivity
 * Purpose: To test if correct intents are being sent after a button is pressed on Main Activity
 */


public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent mainLaunchIntent;
    Button launchFindBtn;
    Button launchClrBtn;

    String[] PresetArray = {"Tomato", "Lobster", "Butter", "Garlic", "Cherry",
            "Apple", "Onions", "Cayenne Pepper", "Salt","Sugar", "Water"};


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
        launchClrBtn=
                (Button) getActivity().findViewById(R.id.clear_button);
    }

    /* Test
     * Goal: To test whether or not SearhableActivity is started after Search menu item is clicked in
     *       Action Menu
     */
    @MediumTest
    public void testSearchableActivityWasLaunchedWithIntent() {
        getInstrumentation().invokeMenuActionSync(getActivity(), R.id.search_menu, 0);

        final Intent launchSearchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchSearchIntent);
        assertTrue(isFinishCalled());

        final String payload = launchSearchIntent.getStringExtra(RecipeActivity.EXTRAS_PAYLOAD_KEY);
        assertEquals("Payload is empty", MainActivity.STRING_PAYLOAD, payload);
    }

    /* Test
     * Goal: to test whether or not RecipeActivity is started after activity button is pressed
     */
    @MediumTest
    public void testRecipeActivityWasLaunchedWithIntent() {
        launchFindBtn.performClick();

        final Intent launchRecipeIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchRecipeIntent);
        //assertTrue(isFinishCalled());

        final String payload = launchRecipeIntent.getStringExtra(RecipeActivity.EXTRAS_PAYLOAD_KEY);
        //assertEquals("Payload is empty", MainActivity.STRING_PAYLOAD, payload);

    }

      /*
     * Helper Method: Inserts a tempoaray List of Ingredients
     */

    public void applyInsertionOfIngredients(){

        Collections.addAll(getActivity().arrayIngredients, PresetArray);

    }

    /*
     * Test
     * Are the Correct Ingredients displayed in list
     */
    public void testCorrectIngredientListView(){


    }
    /* Test
     * Goal: to test whether the Clear button leaves an empty List array
     */
    public void testIsClearButtonWorking(){

        applyInsertionOfIngredients();
        launchClrBtn.performClick();

        ArrayList<String> currentList = getActivity().returnIngredients();
        assertNull("List did not clear", currentList);
    }


}