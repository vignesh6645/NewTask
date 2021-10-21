package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.UserDto;
import com.example.hospital.dto.UserRoleDto;
import com.example.hospital.entity.User;
import com.example.hospital.serviece.UserInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserInterface userInterface;

    @PostMapping("/create")

    public BaseResponse<User> addInfo(@RequestBody UserDto userDTO)   {
        BaseResponse<User> baseResponse=null;
        baseResponse = BaseResponse.<User>builder().Data(userInterface.addUser(userDTO)).build();
        return baseResponse;
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "user login ")
    public BaseResponse<UserRoleDto> generateToken(@RequestBody UserRoleDto userRoleDTO) {
        BaseResponse<UserRoleDto> baseResponse=null;
        baseResponse = BaseResponse.<UserRoleDto>builder().Data(userInterface.generateToken(userRoleDTO)).build();
        return baseResponse;
    }
    @RolesAllowed(value = "USER")
    @GetMapping("/userid/{id}")
    public BaseResponse<User> getUserById(@PathVariable Integer id){
        BaseResponse<User> baseResponse= null;
        baseResponse = BaseResponse.<User>builder().Data(userInterface.getUserById(id)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
    public BaseResponse<User> updateInfo(@RequestBody UserDto userDTO){
        BaseResponse<User> baseResponse= null;
        baseResponse = BaseResponse.<User>builder().Data(userInterface.updateUser(userDTO)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/{offset}/{pageSize}/{name}")
    public PageResponse<User> getUserWithPagination(@PathVariable int offset, @PathVariable int pageSize,
                                                    @PathVariable String name){
        return  userInterface.GetUserWithPagination(offset, pageSize, name);
    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
       // BaseResponse<User> baseResponse=null;
       // baseResponse=BaseResponse.<User>builder().Data(userInterface.deleteUser(id)).build();
        userInterface.deleteUser(id);
        return "Success";
    }
}
