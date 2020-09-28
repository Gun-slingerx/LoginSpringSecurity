package com.example;


import com.example.service.RolePermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = LoginDemoApplication.class)
public class AppTest {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    public void test() {
        rolePermissionService.cacheRolePermissionInfo();
    }
}
