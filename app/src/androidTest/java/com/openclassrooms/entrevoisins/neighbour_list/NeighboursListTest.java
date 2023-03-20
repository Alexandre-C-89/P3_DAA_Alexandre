
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

import android.content.Intent;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de
     * détails est bien lancé
     */
    @Test
    public void clickNeighbourItem_openDetailsScreen_shouldDisplayNeighbour() {
        // Given : We display list of neighbours
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When Click on item of neighbour list
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Then : check details screen is display
        onView(withId(R.id.details_neighbour), isDisplayed()).check(matches(isDisplayed()));
    }

    /**
     * test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
     * le nom de l’utilisateur en question est bien rempli
     */
    @Test
    public void checkTextView_CheckAction_ShouldCheckTextView() {
        // Given :
        Neighbour neighbour = new Neighbour(1, "Jean Dupont", "Avatar", "jump street", "+33 678354619", "it's just me"); // initialiser les autres attributs si nécessaire
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DetailNeighbourActivity.class);
        intent.putExtra(DetailNeighbourActivity.EXTRA_NEIGHBOUR, neighbour);
        ActivityScenario<DetailNeighbourActivity> scenario = ActivityScenario.launch(intent);
        // When :

        // Then :
        onView(withId(R.id.name_detail_neighbour)).check(matches(withText(neighbourName)));
    }

    /**
     * test vérifiant qu’au clic sur le bouton de suppression, la liste d’utilisateurs
     * compte bien un utilisateur en moins
     */


    /**
     * test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
     * favoris.
     */

}