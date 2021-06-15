package com.example.retrofitdemo.api;

import com.example.retrofitdemo.FavoriteFragment;
import com.example.retrofitdemo.HomeActivity;
import com.example.retrofitdemo.model.Account;
import com.example.retrofitdemo.model.AccountLogIn;
import com.example.retrofitdemo.model.AccountSignUp;
import com.example.retrofitdemo.model.BoDe;
import com.example.retrofitdemo.model.EndExam;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.HistoryDetail;
import com.example.retrofitdemo.model.HistoryTest;
import com.example.retrofitdemo.model.KetQuaThi;
import com.example.retrofitdemo.model.SignUpStatus;
import com.example.retrofitdemo.model.StartingTest;
import com.example.retrofitdemo.model.StartingTest2;
import com.example.retrofitdemo.model.Statistic;
import com.example.retrofitdemo.model.TestCreation;
import com.example.retrofitdemo.model.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    APIService apiService=new Retrofit.Builder()
            .baseUrl("https://testing-api-1.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIService.class);
@POST("api/auth/signin")//Dang Nhap
    Call<Token> signIn(@Body AccountLogIn accountLogIn);

@POST("api/auth/signup")
    Call<SignUpStatus> signUp(@Body AccountSignUp accountSignUp);
@GET("api/user/me")
    Call<Account> getInfo(@Header("Authorization") String token);
@GET("api/test")
    Call<ArrayList<BoDe>> getBoDe();
@POST("api/test")
    Call<SignUpStatus> createTest(@Header("Authorization") String token,@Body TestCreation testCreation);
@GET("api/test")
    Call<ArrayList<BoDe>> getTest();
@GET("api/test/{testId}/start")
    Call<StartingTest> startExam(@Header("Authorization") String token,@Path("testId") long testId );
@GET("api/test/{testId}/start2")
    Call<StartingTest2> startExam2(@Header("Authorization") String token, @Path("testId") long testId );
@POST("api/test/{testId}/end")
    Call<SignUpStatus> endExam(@Header("Authorization") String token, @Path("testId") long testId, @Body EndExam endExam);
@GET("api/statistic")
    Call<List<Statistic>> statistic(@Header("Authorization") String token);
@GET("api/user/resetpassword")
    Call<SignUpStatus> resetPassword(@Query("email") String email);
@GET("api/question/savefavorite/{questionId}")
    Call<SignUpStatus> saveFavorite(@Header("Authorization") String token,@Path("questionId") long questionId );

@GET("api/question/getfavorite")
    Call<ArrayList<FavoriteQuestion>> getFavorite(@Header("Authorization") String token);
@GET("api/test/history")
    Call<ArrayList<HistoryTest>> getHistoryTests(@Header("Authorization") String token);
@GET("api/test/history/{historyId}")
    Call<HistoryDetail> getHistoryDetail(@Header("Authorization") String token, @Path("historyId") long historyId);
@GET("api/question/deletefavorite/{questionId}")
    Call<SignUpStatus> delFavoriteQuestion(@Header("Authorization") String token, @Path("questionId") long questionId);
}

