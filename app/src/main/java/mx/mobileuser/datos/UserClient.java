package mx.mobileuser.datos;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClient  {
    private static UserClient instance = null;
    private UserService userService;
    private Retrofit retrofit;

    public UserClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

    public static UserClient getInstance() {
        if (instance != null) return instance;
        return  instance = new UserClient();
    }

    public UserService getUserService(){return userService; }


}
