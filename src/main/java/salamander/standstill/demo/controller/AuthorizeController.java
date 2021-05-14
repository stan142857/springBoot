package salamander.standstill.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import salamander.standstill.demo.dto.AccesstokenDTO;
import salamander.standstill.demo.dto.GithubUser;
import salamander.standstill.demo.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.Redirect.uri}")
    private String Redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                            HttpServletRequest request){        //获取session
        System.out.println("salamander.standstill.demo.controller.AuthorizeController");
        System.out.println("code: "+code +"，state: "+state);

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(client_id);
        accesstokenDTO.setClient_secret(client_secret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(Redirect_uri);
        accesstokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());

        if(user!=null){
            //登录成功
            request.getSession().setAttribute("user",user);  //将user放入session
            return "redirect:/";         //重定向到index页面，而不是简单的去除地址上的code
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}











