package com.example.aib.restauwacja;

import com.example.aib.restauwacja.data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Olek on 2015-06-06.
 */
@EBean
public class RestReservationBackgroundTask {

    @RootContext
    Registration activity;
    @RestService
    RestauwacjaRestClient restClient;

    @Background
    void register(User uzytkownik)
    {
        try
        {
            restClient.setHeader("X-Dreamfactory-Application-Name", "restauwacja");
            User user = restClient.registerNewUser(uzytkownik);
            publishResult(user);
        }
        catch (Exception e)
        {
            publishError(e);
        }
    }

    @UiThread
    void publishResult(User user){
        activity.registerSuccess(user);
    }
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }
}
