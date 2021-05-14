package salamander.standstill.demo.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import salamander.standstill.demo.dto.AccesstokenDTO;
import salamander.standstill.demo.dto.GithubUser;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccesstokenDTO accesstokenDTO){
        System.out.println("salamander.standstill.demo.provider.GithubProvider.getAccessToken");
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String tokenStr = str.split("&")[0].split("=")[1];
            System.out.println(str+" : "+tokenStr);
            return tokenStr;
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public GithubUser getUser(String accessToken){
        System.out.println("salamander.standstill.demo.provider.GithubProvider.getUser");
        System.out.println("  accessToken = "+accessToken);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try(Response response = client.newCall(request).execute()){
            String str = response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);

            return githubUser;
        } catch (IOException e) {   }
        return null;
    }
}
