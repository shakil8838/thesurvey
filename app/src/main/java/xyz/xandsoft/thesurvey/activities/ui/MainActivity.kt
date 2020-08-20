package xyz.xandsoft.thesurvey.activities.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import xyz.xandsoft.thesurvey.R
import xyz.xandsoft.thesurvey.adapters.CheckboxAdapter
import xyz.xandsoft.thesurvey.adapters.DropdownAdapter
import xyz.xandsoft.thesurvey.adapters.RadioAdapter
import xyz.xandsoft.thesurvey.interfaces.OnItemClicked
import xyz.xandsoft.thesurvey.model.datamodel.MainResponse
import xyz.xandsoft.thesurvey.utils.showToast

class MainActivity : AppCompatActivity(), OnItemClicked {

    private val TAG = "MainActivity"

    private var responseList: MutableList<MainResponse> = ArrayList()

    private lateinit var dropDownList: RecyclerView
    private lateinit var radioList: RecyclerView
    private lateinit var checkboxList: RecyclerView

    private lateinit var dropdownQuesiton: TextView
    private lateinit var checkboxQuesiton: TextView

    private lateinit var questionThree: EditText
    private lateinit var questionFour: EditText

    private var isExpand = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAll()

        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://example-response.herokuapp.com/getSurvey",
            {
                Log.e(TAG, "onCreate: $it")
                extractJson(it)
            },
            {
                Log.e(TAG, "onFailure: ${it.message}")
            }
        )
        Volley.newRequestQueue(this).add(stringRequest)
    }

    private fun initAll() {

        questionThree = findViewById(R.id.question_three)
        questionFour = findViewById(R.id.question_four)

        dropdownQuesiton = findViewById(R.id.question_two)

        dropDownList = findViewById(R.id.question_two_options)
        dropDownList.layoutManager = LinearLayoutManager(this)

        radioList = findViewById(R.id.question_one_options)
        radioList.layoutManager = LinearLayoutManager(this)

        checkboxList = findViewById(R.id.question_five_options)
        checkboxList.layoutManager = LinearLayoutManager(this)

        checkboxQuesiton = findViewById(R.id.question_five)

        findViewById<CardView>(R.id.question_two_lay).setOnClickListener {
            if (isExpand) {
                dropDownList.visibility = View.GONE
                isExpand = false
                findViewById<ImageView>(R.id.drop_up_img).setImageDrawable(
                    resources.getDrawable(R.drawable.ic_drop_down)
                )
            } else {
                dropDownList.visibility = View.VISIBLE
                isExpand = true
                findViewById<ImageView>(R.id.drop_up_img).setImageDrawable(
                    resources.getDrawable(R.drawable.ic_drop_up)
                )
            }
        }
    }

    private fun extractJson(rawJson: String) {

        val mainJson = JSONArray(rawJson)
        Log.e(TAG, "extractJson: ${mainJson.length()}")

        var count = 0

        while (count < mainJson.length()) {

            val responseBody = mainJson.getJSONObject(count)

            responseList.add(
                MainResponse(
                    responseBody.getString("question"),
                    responseBody.getString("type"),
                    responseBody.getString("options"),
                    responseBody.getBoolean("required")
                )
            )

            count++
        }

        // Dropdown section
        when {
            responseList[0].type?.equals("dropdown")!! -> {

                dropdownQuesiton.text = responseList[0].question
                dropDownList.adapter = DropdownAdapter(
                    this,
                    splitString(responseList[0].options!!),
                    this
                )

            }
            responseList[1].type?.equals("dropdown")!! -> {

                dropdownQuesiton.text = responseList[1].question
                dropDownList.adapter = DropdownAdapter(
                    this,
                    splitString(responseList[1].options!!),
                    this
                )
            }
            responseList[2].type?.equals("dropdown")!! -> {
                dropdownQuesiton.text = responseList[2].question
                dropDownList.adapter = DropdownAdapter(
                    this,
                    splitString(responseList[2].options!!),
                    this
                )
            }
            responseList[3].type?.equals("dropdown")!! -> {
                dropdownQuesiton.text = responseList[3].question
                dropDownList.adapter = DropdownAdapter(
                    this,
                    splitString(responseList[3].options!!),
                    this
                )
            }
            responseList[4].type?.equals("dropdown")!! -> {
                dropdownQuesiton.text = responseList[4].question
                dropDownList.adapter = DropdownAdapter(
                    this,
                    splitString(responseList[4].options!!),
                    this
                )
            }
        }

        // Radio section
        when {
            responseList[0].type?.equals("multiple choice")!! -> {

                findViewById<TextView>(R.id.question_one).text = responseList[0].question
                radioList.adapter = RadioAdapter(
                    this,
                    splitString(responseList[0].options!!),
                    this
                )

            }
            responseList[1].type?.equals("multiple choice")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[1].question
                radioList.adapter = RadioAdapter(
                    this,
                    splitString(responseList[1].options!!),
                    this
                )
            }
            responseList[2].type?.equals("multiple choice")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[2].question
                radioList.adapter = RadioAdapter(
                    this,
                    splitString(responseList[2].options!!),
                    this
                )
            }
            responseList[3].type?.equals("multiple choice")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[3].question
                radioList.adapter = RadioAdapter(
                    this,
                    splitString(responseList[3].options!!),
                    this
                )
            }
            responseList[4].type?.equals("multiple choice")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[4].question
                radioList.adapter = RadioAdapter(
                    this,
                    splitString(responseList[4].options!!),
                    this
                )
            }
        }

        // Question section 1
        when {
            responseList[0].type?.equals("text")!! -> {
                questionThree.hint = responseList[0].question
            }
            responseList[1].type?.equals("text")!! -> {
                questionThree.hint = responseList[1].question
            }
            responseList[2].type?.equals("text")!! -> {
                questionThree.hint = responseList[2].question
            }
            responseList[3].type?.equals("text")!! -> {
                questionThree.hint = responseList[3].question
            }
            responseList[4].type?.equals("text")!! -> {
                questionThree.hint = responseList[4].question
            }
        }

        // Question section 2
        when {
            responseList[0].type?.equals("number")!! -> {
                questionFour.hint = responseList[0].question
            }
            responseList[1].type?.equals("number")!! -> {
                questionFour.hint = responseList[1].question
            }
            responseList[2].type?.equals("number")!! -> {
                questionFour.hint = responseList[2].question
            }
            responseList[3].type?.equals("number")!! -> {
                questionFour.hint = responseList[3].question
            }
            responseList[4].type?.equals("number")!! -> {
                questionFour.hint = responseList[4].question
            }
        }

        // Checkbox section
        when {
            responseList[0].type?.equals("Checkbox")!! -> {
                checkboxQuesiton.text = responseList[0].question
                checkboxList.adapter = CheckboxAdapter(
                    this,
                    splitString(responseList[0].options!!)
                )
            }
            responseList[1].type?.equals("Checkbox")!! -> {
                checkboxQuesiton.text = responseList[1].question
                checkboxList.adapter = CheckboxAdapter(
                    this,
                    splitString(responseList[1].options!!)
                )
            }
            responseList[2].type?.equals("Checkbox")!! -> {
                checkboxQuesiton.text = responseList[2].question
                checkboxList.adapter = CheckboxAdapter(
                    this,
                    splitString(responseList[2].options!!)
                )
            }
            responseList[3].type?.equals("Checkbox")!! -> {
                checkboxQuesiton.text = responseList[3].question
                checkboxList.adapter = CheckboxAdapter(
                    this,
                    splitString(responseList[3].options!!)
                )
            }
            responseList[4].type?.equals("Checkbox")!! -> {
                checkboxQuesiton.text = responseList[4].question
                checkboxList.adapter = CheckboxAdapter(
                    this,
                    splitString(responseList[4].options!!)
                )
            }
        }
    }

    private fun splitString(rawString: String): Array<String> {
        return rawString.split("\\s*,\\s*".toRegex()).toTypedArray()
    }

    override fun itemClicked(text: String) {
        showToast(text)
    }
}