import com.my.service.UserService;
import com.my.serviceImpl.UserServiceByDBImpl;
import com.my.serviceImpl.UserServiceByFileImpl;
import org.junit.Test;

public class UserTest {

    public void show(UserService userService){


    }

    @Test
    public void test1(){
        UserService db=new UserServiceByDBImpl();
        String user = db.createUser("123");
        UserService file=new UserServiceByFileImpl();
        String user1 = file.createUser("2345");
    }


}
