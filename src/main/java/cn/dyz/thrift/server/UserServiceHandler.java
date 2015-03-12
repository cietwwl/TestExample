package cn.dyz.thrift.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import cn.dyz.thrift.demo.gen.User;
import cn.dyz.thrift.demo.gen.UserNotFound;
import cn.dyz.thrift.demo.gen.UserService.Iface;



public class UserServiceHandler implements Iface {

	public List<User> getUsers() throws TException {
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setId(1);
		user.setUsername("user1");
		user.setPassword("pwd1");
		list.add(user);
		User user2 = new User();
		user2.setId(1);
		user2.setUsername("user2");
		user2.setPassword("pwd2");
		list.add(user2);
		return list;
	}

	public User getUserByName(String username) throws UserNotFound, TException {
		if ("user1".equals(username)) {
			User user = new User();
			user.setId(1);
			user.setUsername("user1");
			user.setPassword("pwd1");
			return user;
		} else {
			throw new UserNotFound();
		}
	}

}

