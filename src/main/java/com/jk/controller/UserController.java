package com.jk.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.jk.model.Customer;
import com.jk.model.UserBean;
import com.jk.service.UserService;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	

	@Autowired(required = false)
     RedisTemplate redisTemplate;
	
	
	@RequestMapping("toMain")
	public String toMain(ModelMap map){
		List<UserBean> user = userService.queryUser();
		map.put("data", user);
		return "view";
	}
	//保存
	@RequestMapping("toadduser")
	public ModelAndView toadduser(UserBean user){
		ModelAndView result = new ModelAndView("redirect:/user/toMain");
		userService.toadduser(user);
		return result;
	}
	//新增
	@RequestMapping("tosave")
	public String tosave(){
		
		return "adduser";
	}
	//删除
	@RequestMapping("/todelete/{id}")
	public ModelAndView todelete(@PathVariable Integer id, RedirectAttributes ra){
		 ModelAndView result = new ModelAndView("redirect:/user/toMain");
		userService.todelete(id);
		  ra.addFlashAttribute("msg", "删除成功!");
		return result;
	}
	//修改 查询
	@RequestMapping("toMainbyid/{id}")
	
	public String  toMainbyid(Model m,@PathVariable Integer id){
		UserBean user = userService.toMainbyid(id);
		m.addAttribute("data", user);
		return "updateuser";
	}
	
	
	//mogodb查询
	@RequestMapping("/list")
	@ResponseBody
    public List<Customer> dobegin() throws Exception {
     List<Customer> list =  mongoTemplate.findAll(Customer.class);//也可以
        return list;
    }
	//mogodb新增
	@RequestMapping("addCustomer")
    @ResponseBody
    public Boolean addCustomer(Customer customer){
		try {
			customer.setCarNumber("爱国");
			customer.setCaraihao("篮球");
			mongoTemplate.save(customer, "customer");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//mogodb删除	
	@RequestMapping("deletemg")
    @ResponseBody
    public void deletemg(Customer customer) {
		String id= customer.set_id("5beadb263480d5277083c789");
		Query query=new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query,"customer");
	}
	//mogod修改
	@RequestMapping("updatemg")
    @ResponseBody
    public void updatemg(Customer customer) {
		Update update = new Update();   
		update.set("carNumber","修改姓名");
		update.set("caraihao", "修改爱好");
		
		mongoTemplate.updateFirst(new Query(Criteria.where("5beacd563480d51a8072e3b7").is(customer.get_id())), update, Customer.class);

	}

	@RequestMapping("add")
    @ResponseBody
	public void add(String key,Object value){
	        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
	        opsForValue.append( "state_"+1, "爱国");
	    }
	
	
}
