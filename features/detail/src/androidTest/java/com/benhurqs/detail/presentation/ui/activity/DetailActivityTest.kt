package com.benhurqs.detail.presentation.ui.activity

import android.content.Intent
import android.os.Build
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.benhurqs.base.actions.Actions
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.PricingInfo
import com.benhurqs.base.utils.ImovelFormatedUtils
import com.benhurqs.detail.R
import com.benhurqs.detail.presentation.contract.DetailView
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailActivityTest{

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(DetailActivity::class.java)

    lateinit var view: DetailView

    @Before
    fun setUp(){

        val intent = Intent()
        intent.putExtra(Actions.IMOVEL, getImovel())
        mActivityTestRule.launchActivity(intent)

        view = (mActivityTestRule.activity as DetailActivity)

    }

    @Test
    fun test_load_price(){
        callFuction { view.loadPrice(getImovel()) }

        Espresso.onView(ViewMatchers.withId(R.id.detail_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("R$ 123,00")))
    }

    @Test
    fun test_load_bathroom(){
        callFuction { view.loadBathroom(2) }

        Espresso.onView(ViewMatchers.withId(R.id.detail_bathroom_qtd))
            .check(ViewAssertions.matches(ViewMatchers.withText("02")))
    }

    @Test
    fun test_load_bedroom(){
        callFuction { view.loadBedroom(3) }

        Espresso.onView(ViewMatchers.withId(R.id.detail_bed_qtd))
            .check(ViewAssertions.matches(ViewMatchers.withText("03")))
    }

    @Test
    fun test_load_parking(){
        callFuction { view.loadParking(5) }

        Espresso.onView(ViewMatchers.withId(R.id.detail_parking_qtd))
            .check(ViewAssertions.matches(ViewMatchers.withText("05")))
    }

    @Test
    fun test_load_description(){
        callFuction { view.loadDescription(1,2,3,4,"5",6f) }

        val result = mActivityTestRule.activity.getString(
            R.string.detail_description,
            1,
            2,
            3,
            4,
            ImovelFormatedUtils.formatValue("5"),
            ImovelFormatedUtils.formatValue(6f))

        Espresso.onView(ViewMatchers.withId(R.id.detail_description))
            .check(ViewAssertions.matches(ViewMatchers.withText(result)))
    }

    @Test
    fun test_load_images(){
        var list = ArrayList<String>().apply {
            add("http://grupozap-code-cha…naws.com/images/pic9.jpg")
            add("http://grupozap-code-cha…naws.com/images/pic8.jpg")
            add("http://grupozap-code-cha…naws.com/images/pic18.jpg")
            add("http://grupozap-code-cha…naws.com/images/pic1.jpg")
        }
        callFuction { view.loadImages(list) }
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.detail_view_pager)).perform(ViewActions.swipeLeft())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.detail_view_pager)).perform(ViewActions.swipeLeft())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.detail_view_pager)).perform(ViewActions.swipeRight())
        Thread.sleep(1000)
    }

    @Test
    fun test_click_back(){
        Espresso.onView(ViewMatchers.withId(R.id.detail_back)).perform(ViewActions.click())
        Thread.sleep(2000)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            assertTrue(mActivityTestRule.activity.isDestroyed)
        }
    }


    /**************** HELPERS *****************/
    fun getImovel(): Imovel{
        return Imovel().apply {
            pricingInfos = PricingInfo().apply {
                price = 123f
            }
        }
    }


    fun callFuction(function: () -> Unit){
        mActivityTestRule.run {
            runOnUiThread {
                function()
            }
        }
    }

    fun onClickView(viewId: Int){
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.click())
    }

}