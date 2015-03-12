namespace java thrift.demo.gen
namespace py thrift.demo
struct User{
	1: i32 id,
	2: string username,
	3: string password
}

exception UserNotFound{
	1:string message
}

service UserService{
	list<User> getUsers(),
	User getUserByName(1:string username) throws(1:UserNotFound unf)
}

