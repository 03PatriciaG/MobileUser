package mx.mobileuser.datos;

import mx.mobileuser.modelo.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface UserService {
    @GET("api/?results=50")
    Call<UserResponse> getUsers();

    @GET("api/?results=50")
    Call<UserResponse>getFilterUser(@Query("gender") String genre);
}
