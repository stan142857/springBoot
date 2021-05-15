package salamander.standstill.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import salamander.standstill.demo.mapper.UserMapper;
import salamander.standstill.demo.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    //以cookie登录

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);

                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
