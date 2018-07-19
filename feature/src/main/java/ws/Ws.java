package ws;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class Ws {

    RequestQueue queue;
    String method;
    String url = "http://gestion.test/";
    String _url;
    HashMap params;
    Context context;
    private WsInterface mCallback;

    public Ws(Context _context, String _url, String _method, HashMap _params){
        this._url = _url;
        this.method = _method;
        this.params = _params;
        this.context = _context;
        this.queue = Volley.newRequestQueue(_context);
        this.request();
    }
    public Ws(Context _context, String _url, String _method){
        this._url = _url;
        this.method = _method;
        this.queue = Volley.newRequestQueue(_context);
        this.request();
    }

    public void request(){

        if(this.method == "POST"){
            this.postWs();
        }
        if(this.method == "GET"){
            this.getWs();
        }



    }

    public void  getWs(){

        this._url = this.url+_url;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, _url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());

                        CharSequence text = "Finalizo petición a search!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        mCallback.requestCompleted();



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", "error");
                        mCallback.requestEndedWithError(error);
                    }
                }
        );
        queue.add(getRequest);
    }

    public void postWs(){

        this._url = this.url+_url;
        StringRequest postRequest = new StringRequest(Request.Method.POST, _url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        mCallback.requestCompleted();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "error");
                        mCallback.requestEndedWithError(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
        };
        queue.add(postRequest);
    }
}

