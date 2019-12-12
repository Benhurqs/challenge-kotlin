package com.benhurqs.mainlist.presentation.ui.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.benhurqs.mainlist.R
import com.benhurqs.mainlist.presentation.contract.MainListView
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ListActivityTest{

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ListActivity::class.java)

    lateinit var view: MainListView

    @Before
    fun setUp(){
        view = (mActivityTestRule.activity as ListActivity)
    }

    @Test
    fun test_if_loading_show(){
        checkIfDisplayed(R.id.laoding_content)
    }

    @Test
    fun test_if_show_filter_after_click_filter_icon(){
        onClickView(R.id.filter_btn)
        checkIfDisplayed(R.id.filter)
    }

    @Test
    fun test_show_filter(){
        callFuction { view.showFilter() }
        checkIfDisplayed(R.id.filter)
    }

    @Test
    fun test_hide_filter(){
        callFuction { view.hideFilter() }
        checkIfNotDisplayed(R.id.filter)
    }

    @Test
    fun test_show_error(){
        callFuction { view.showError() }
        checkIfDisplayed(R.id.error_mesg)
        checkIfDisplayed(R.id.error_retry)
    }

    @Test
    fun test_hide_error(){
        callFuction { view.hideError() }
        checkIfNotDisplayed(R.id.error_mesg)
        checkIfNotDisplayed(R.id.error_retry)
    }

    @Test
    fun test_show_progress(){
        callFuction { view.showProgress() }
        checkIfDisplayed(R.id.laoding_content)
    }

    @Test
    fun test_hide_progress(){
        callFuction { view.hideProgress() }
        checkIfNotDisplayed(R.id.laoding_content)
    }

    @Test
    fun test_hide_filter_close_button(){
        callFuction { view.showFilter() }
        callFuction { view.hideCloseFilter() }

        checkIfDisplayed(R.id.filter)
        checkIfNotDisplayed(R.id.filter_close_btn)
    }

    @Test
    fun test_show_filter_close_button(){
        callFuction { view.showFilter() }
        callFuction { view.showCloseFilter() }

        checkIfDisplayed(R.id.filter)
        checkIfDisplayed(R.id.filter_close_btn)
    }

    @Test
    fun test_if_close_filter_when_close_button_is_clicked(){
        callFuction { view.hideProgress() }
        callFuction { view.showFilter() }
        callFuction { view.showCloseFilter() }

        checkIfDisplayed(R.id.filter)
        checkIfDisplayed(R.id.filter_close_btn)


        Thread.sleep(1000)
        onClickView(R.id.filter_close_btn)
        Thread.sleep(1000)

        checkIfNotDisplayed(R.id.filter)
    }

    @Test
    fun test_set_title(){
        val title = "TEST TITLE"
        callFuction { view.hideProgress() }
        callFuction { view.hideFilter() }
        callFuction { view.setTitle(title) }

        onView(withId(R.id.list_title)).check(ViewAssertions.matches(withText(title)))
    }

    @Test
    fun test_click_zap_filter(){
        callFuction { view.hideProgress() }
        callFuction { view.showFilter() }
        callFuction { view.showCloseFilter() }

        Thread.sleep(1000)
        checkIfDisplayed(R.id.filter)

        onClickView(R.id.filter_zap)
        Thread.sleep(1000)

        checkIfNotDisplayed(R.id.filter)
        onView(withId(R.id.list_title)).check(ViewAssertions.matches(withText("Zap")))

    }

    @Test
    fun test_click_viva_filter(){
        callFuction { view.hideProgress() }
        callFuction { view.showFilter() }
        callFuction { view.showCloseFilter() }

        Thread.sleep(1000)
        checkIfDisplayed(R.id.filter)

        onClickView(R.id.filter_viva_real)
        Thread.sleep(1000)

        checkIfNotDisplayed(R.id.filter)
        onView(withId(R.id.list_title)).check(ViewAssertions.matches(withText("Viva Real")))

    }


    /**************** HELPERS *****************/
    fun callFuction(function: () -> Unit){
        mActivityTestRule.run {
            runOnUiThread {
                function()
            }
        }
    }

    fun checkIfDisplayed(viewId: Int){
        onView(withId(viewId)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkIfNotDisplayed(viewId: Int){
        onView(withId(viewId)).check(ViewAssertions.matches(not(isDisplayed())))
    }

    fun onClickView(viewId: Int){
        onView(withId(viewId)).perform(ViewActions.click())
    }
}