package com.sample.wipro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var items  = ArrayList<MyData>()

        val _recyclerView: RecyclerView = findViewById(R.id.recycler)

        _recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val queue = Volley.newRequestQueue(this)

        val url: String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->

                    var strResp = response.toString()
                    val jsonObj: JSONObject = JSONObject(strResp)
                    var heading:String = jsonObj.get("title").toString()
                    setTitle(heading)

                    val jsonArray: JSONArray = jsonObj.getJSONArray("rows")

                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        //adding some dummy data to the list

                        items.add(MyData(jsonInner.get("title").toString(),jsonInner.get("description").toString(),jsonInner.get("imageHref").toString()))
                    }
                    val adapter = RecyclerViewAdapter(items)
                    _recyclerView.adapter = adapter
                },
                Response.ErrorListener {  })
        queue.add(stringReq)
    }

}





