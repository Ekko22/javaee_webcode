package com.learn.Controller;

import com.learn.Domain.User;
import com.learn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public boolean login(@RequestBody User user) {
        System.out.println(user);
        User user1 = userService.selectOne(user);
        return user1 != null;
    }

    @PutMapping
    public boolean update(@RequestBody User user, @RequestParam("id") int id) {
        return userService.updateById(id, user);
    }

    //    新增用户
    @PostMapping("/add")
    public boolean add(@RequestBody User user) {
        return userService.insert(user);
    }

    //用户分页查询
    @GetMapping("/page")
    public List<User> page(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.selectPage(page - 1, size);
    }

    //用户总数
    @GetMapping("/count")
    public int count() {
        return userService.count();
    }

    //根据id查询用户
    @GetMapping("/{id}")
    public List<User> selectOne(@PathVariable("id") int id) {
        return userService.selectOneUser(id);
    }

    //    根据id删除用户
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return userService.deleteById(id);
    }
//    根据name查询用户
    @GetMapping("/name")
    public List<User> selectByName(@RequestParam("name") String name) {
        return userService.selectByName(name);
    }
}
