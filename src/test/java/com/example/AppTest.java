package com.example;


import com.example.dao.UserMapper;
import com.example.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = LoginDemoApplication.class)
public class AppTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = userMapper.selectByPrimaryKey(new Long("1072806377661009920"));
        System.out.println(user.getNickname());
    }
}
