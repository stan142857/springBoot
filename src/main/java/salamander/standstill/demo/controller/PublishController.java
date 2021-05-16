package salamander.standstill.demo.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import salamander.standstill.demo.mapper.QuestionMapper;
import salamander.standstill.demo.mapper.UserMapper;
import salamander.standstill.demo.model.Question;
import salamander.standstill.demo.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    //收到的是get   去确认页面
    //收到的是post  去确认请求
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublic(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model
    ){
        //传递到model中
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //错误提示
        if(title==null || title ==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(description==null || description ==""){
            model.addAttribute("error","补充不能为空");
            return "publish";
        }

        if(tag==null || tag ==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        if(user == null){
            //未登录，给model传递一个错误提示
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        //数据库插入
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getID());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.create(question);
        //重定向到首页
        return "redirect:/";
    }
}
