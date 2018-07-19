package ws;

import com.android.volley.VolleyError;

public interface WsInterface {

    public void requestStarted();
    public void requestCompleted();
    public void requestEndedWithError(VolleyError error);

}
