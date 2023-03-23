
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
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
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static java.util.function.Predicate.not;
import java.lang.Deprecated;

import android.annotation.SuppressLint;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

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
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));// click sur le premier élément
        // Then : check details screen is display
        onView(withId(R.id.details_neighbour)).check(matches(isDisplayed()));
        // vérifier que le nom du voisin est affiché sur l'écran de détails
        onView(withId(R.id.name_detail_neighbour)).check(matches(withText("Jack"))); // modifier le nom selon le voisin sélectionné
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
        onView(withId(R.id.name_detail_neighbour)).check(matches(withText(neighbour.getName())));
    }

    /**
     * test vérifiant qu’au clic sur le bouton de suppression, la liste d’utilisateurs
     * compte bien un utilisateur en moins
     */
    @Test
    public void deleteUser_CheckListAction_ShouldRemoveUser() {
        // Given:
        List<Neighbour> initialList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS; // Je récupère la liste de voisins
        Neighbour neighbourToDelete = initialList.get(0); // je choisis un voisins dans la liste
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DetailNeighbourActivity.class);
        intent.putExtra(DetailNeighbourActivity.EXTRA_NEIGHBOUR, neighbourToDelete);
        ActivityScenario<DetailNeighbourActivity> scenario = ActivityScenario.launch(intent);

        // When:
        onView(withId(R.id.details_neighbour)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction())); // Je supprime le voisins

        // Then:
        List<Neighbour> finalList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(finalList.size(), is(initialList.size() - 1));
        assertFalse(finalList.contains(neighbourToDelete));
    }


    /**
     * test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
     * favoris.
     */

    @Test
    public void favoritesTab_ShouldOnlyDisplayFavoriteNeighbours() {
        // Given: at least one neighbor marked as favorite
        Neighbour favouriteNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        favouriteNeighbour.setFavorite(true);
        Neighbour nonFavouriteNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(1);
        nonFavouriteNeighbour.setFavorite(false);

        // When: launching the activity and selecting the Favorites tab
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DetailNeighbourActivity.class);
        intent.putExtra(DetailNeighbourActivity.EXTRA_NEIGHBOUR, favouriteNeighbour);
        ActivityScenario<DetailNeighbourActivity> scenario = ActivityScenario.launch(intent);
        onView(withId(R.id.add_favorite)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("FAVORITES")).perform(click());

        // Then: only the favorite neighbor is displayed
        onView(withId(R.id.list_neighbours)).check(matches(hasDescendant(withText(favouriteNeighbour.getName()))));
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(favouriteNeighbour.getName()))))
                .check(matches(hasDescendant(withId(R.id.list_favorite_neighbours)).matches(isDisplayed())));

}