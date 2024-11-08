package hr.management.system.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    //private static final String Base_URL = "http://10.0.2.2:5000/";
    private static final String Base_URL = "http://sahilsk99.pythonanywhere.com/";

    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
