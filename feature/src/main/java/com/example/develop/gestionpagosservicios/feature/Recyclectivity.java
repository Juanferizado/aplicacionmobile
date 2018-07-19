package com.example.develop.gestionpagosservicios.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.Movie;
import adapters.MoviesAdapter;

public class Recyclectivity extends AppCompatActivity  {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    private Boolean pay;
    private String total, genre, year, customer, invoice;
    String url_base = "http://muniservipagos.com/api/";
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclectivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonPay);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = url_base+"pay/";
                wsPost(url, view);
            }
        });

        // Instantiate the RequestQueue.
        Intent intent = getIntent();
        String code = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = url_base+"search/?search="+code;

        wsGET(url);

        setSupportActionBar(toolbar);
    }


    public void wsPost(String url, View _view){

        view = _view;
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)  {
                        msjSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error ) {

                        msjError(error);

                    }
        } ) {

            @Override
            protected Map<String, String> getParams() throws JSONException {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("pay_dates", "sombra");
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("username", "DAta");


                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluQGFkbWluLmNvbSIsImV4cCI6MTUyODE4NDA3Nywib3JpZ19pYXQiOjE1MjgxODA0NzcsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIiwidXNlcl9pZCI6MX0.DzMuy6XkiRs5cvsNnvlQM2xHT2FQuFv7ODcM2zx4i8g");
                return headers;

            }

        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void wsGET(String url){

        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)  {
                        try{

                            JSONArray array = new JSONArray(response);
                            if( array.getJSONObject(0).has("total")) {
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject row = array.getJSONObject(i);
                                    customer = row.getJSONObject("customer_obj").get("full_name").toString();
                                    total =  row.getString("total");
                                    year = row.getString("year");
                                    invoice = row.getString("id");
                                    genre = row.getString("month");
                                    pay = row.getBoolean("pay_status");

                                    Movie mov = new Movie(total, genre, year, pay, customer, invoice);
                                    movieList.add(mov);
                                    mAdapter.notifyDataSetChanged();
                                }
                            }

                        } catch (JSONException e) {

                            throw new RuntimeException(e);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                //customer = (TextView) findViewById(R.id.customer);

                //customer.setText( error.getMessage() + ":::Error");

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluQGFkbWluLmNvbSIsImV4cCI6MTUyODE4NDA3Nywib3JpZ19pYXQiOjE1MjgxODA0NzcsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIiwidXNlcl9pZCI6MX0.DzMuy6XkiRs5cvsNnvlQM2xHT2FQuFv7ODcM2zx4i8g");
                return headers;
            }};

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void msjError( VolleyError error) {

        Snackbar.make(view, "Se presionó el FAB"+ error, Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();

    }
    public void msjSuccess( String response) {

        Snackbar.make(view, response, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
