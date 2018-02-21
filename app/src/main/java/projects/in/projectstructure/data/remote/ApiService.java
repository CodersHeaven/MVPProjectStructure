package projects.in.projectstructure.data.remote;

import projects.in.projectstructure.data.models.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/webservices/users/doLogin")
    @FormUrlEncoded
    Call<LoginResponse> performLogin(@Field("username") String userName,
                                     @Field("password") String password,
                                     @Field("accessToken") String accessToken,
                                     @Field("deviceToken") String deviceToken);
} 