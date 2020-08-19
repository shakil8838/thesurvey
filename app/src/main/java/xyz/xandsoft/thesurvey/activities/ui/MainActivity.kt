package xyz.xandsoft.thesurvey.activities.ui

import android.os.Bundle
import android.util.Log
import android.view.View
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
import xyz.xandsoft.thesurvey.adapters.DropdownAdapter
import xyz.xandsoft.thesurvey.model.datamodel.MainResponse

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var responseList: MutableList<MainResponse> = ArrayList()

    private lateinit var dropDownList: RecyclerView
    private lateinit var radioList: RecyclerView
    private lateinit var checkboxList: RecyclerView

    private lateinit var dropdownQuesiton: TextView

    private var isExpand = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAll()

        val stringRequest = StringRequest(
            Request.Method.GET,
            " https://example-response.herokuapp.com/getSurvey",
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

    fun initAll() {

        dropdownQuesiton = findViewById(R.id.question_two)

        dropDownList = findViewById(R.id.question_two_options)
        dropDownList.layoutManager = LinearLayoutManager(this)

        findViewById<CardView>(R.id.question_two_lay).setOnClickListener {
            if (isExpand) {
                dropDownList.visibility = View.GONE
                isExpand = false
            } else {
                dropDownList.visibility = View.VISIBLE
                isExpand = true
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
                val ddrList: Array<String> =
                    responseList[0].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)

            }
            responseList[1].type?.equals("dropdown")!! -> {

                dropdownQuesiton.text = responseList[1].question

                val ddrList: Array<String> =
                    responseList[1].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()

                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
            responseList[2].type?.equals("dropdown")!! -> {
                dropdownQuesiton.text = responseList[2].question
                val ddrList: Array<String> =
                    responseList[2].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
            responseList[3].type?.equals("dropdown")!! -> {
                dropdownQuesiton.text = responseList[3].question
                val ddrList: Array<String> =
                    responseList[3].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
            responseList[4].type?.equals("dropdown")!! -> {
                dropdownQuesiton.text = responseList[4].question
                val ddrList: Array<String> =
                    responseList[4].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
        }

        /*// Checkbox section
        when {
            responseList[0].type?.equals("dropdown")!! -> {

                findViewById<TextView>(R.id.question_one).text = responseList[0].question
                val ddrList: Array<String> =
                    responseList[0].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)

            }
            responseList[1].type?.equals("dropdown")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[1].question
                val ddrList: Array<String> =
                    responseList[1].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
            responseList[2].type?.equals("dropdown")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[2].question
                val ddrList: Array<String> =
                    responseList[2].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
            responseList[3].type?.equals("dropdown")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[3].question
                val ddrList: Array<String> =
                    responseList[3].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
            responseList[4].type?.equals("dropdown")!! -> {
                findViewById<TextView>(R.id.question_one).text = responseList[4].question
                val ddrList: Array<String> =
                    responseList[4].options?.split("\\s*,\\s*".toRegex())!!.toTypedArray()
                dropDownList.adapter = DropdownAdapter(this, ddrList)
            }
        }*/
    }
}