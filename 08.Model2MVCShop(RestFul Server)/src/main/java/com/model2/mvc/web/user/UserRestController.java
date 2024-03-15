package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

//==> 회원관리 RestController
@RestController
@RequestMapping("/rest/user/*")
public class UserRestController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	// setter Method 구현 않음

	public UserRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/addUser/{message}", method=RequestMethod.GET )
	public Map<String, Object> addUser(@PathVariable String message) throws Exception{
	
		System.out.println("/user/addUser : GET");
		System.out.println("message : "+message);
		
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("message", message);
		
		return map;
	}

	@RequestMapping(value = "json/addUser", method = RequestMethod.POST)
	public User addUser( /* @ModelAttribute("user") User user */@RequestBody User user) throws Exception {

		System.out.println("/user/addUser : POST");
		// Business Logic
		userService.addUser(user);

		return userService.getUser(user.getUserId());
	}

	@RequestMapping(value = "json/getUser/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) throws Exception {

		System.out.println("/user/json/getUser : GET");

		// Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping(value = "json/login", method = RequestMethod.POST)
	public User login(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("/user/json/login : POST");
		// Business Logic
		System.out.println("::" + user);
		User dbUser = userService.getUser(user.getUserId());

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}

		return dbUser;
	}

	@RequestMapping( value="/json/updateUser/{userId}", method=RequestMethod.GET )
	public Map<String,Object> updateUser( @PathVariable("userId") String userId  ) throws Exception{

		System.out.println("/user/updateUser : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user", user);
		
		return map;
	}
	

	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		
		return "redirect:/user/getUser?userId="+user.getUserId();
	}
}