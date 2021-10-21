package com.example.hospital.servieceImplements;

import com.example.hospital.Utill.JwtUtil;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.UserDto;
import com.example.hospital.dto.UserRoleDto;
import com.example.hospital.entity.Role;
import com.example.hospital.entity.User;
import com.example.hospital.exception.ControllerException;
import com.example.hospital.repository.RoleRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.serviece.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServieceImplements implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User addUser(UserDto userDTO) {

        User user = new User();
        user.setName(userDTO.getUserName());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(userDTO.getPassword()));

        List<Role> roleList=new LinkedList<>();
        userDTO.getRoles().stream().forEachOrdered(role -> {
            Role role1=new Role();
            role1.setRoleName(role.getRoleName());
            roleList.add(role1);
        });
        user.setListofrole(roleList);
        user=userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user= userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public User updateUser(UserDto userDTO) {
        User existUser = userRepository.findById(userDTO.getId()).orElse(null);
        existUser.setName(userDTO.getUserName());
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        existUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        List<Role> roleList = new LinkedList<>();
        userDTO.getRoles().forEach(role -> {
            Role role1 = roleRepository.findById(role.getId()).orElse(null);
            role1.setRoleName(role.getRoleName());
            roleList.add(role1);
        });
        existUser.setListofrole(roleList);
        userRepository.save(existUser);

        return existUser;
    }

    @Override
    public PageResponse<User> GetUserWithPagination(int offset, int pageSize, String name) {
            PageResponse pageResponse = new PageResponse();
            try {
                Pageable paging = PageRequest.of(offset, pageSize);
                Page<User> Users = userRepository.searchAllByNameLike("%"+ name + "%", paging);
                pageResponse.setResponse(Users);
                pageResponse.setRecordCount(Users.getTotalPages());
            }catch (NoSuchElementException e){
                throw new ControllerException("404","Not found");
            }

        return pageResponse;
    }

    @Override
    public User deleteUser(Integer id) {
        User user = new User();
        userRepository.deleteById(id);
        return user;
    }
    @Override
    public UserRoleDto generateToken(UserRoleDto userRoleDTO) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        List<Role> roles = new LinkedList<>();

        try {
            Optional<User> user = userRepository.findByName(userRoleDTO.getUserName());
            boolean status = bcrypt.matches(userRoleDTO.getPassword(), user.get().getPassword());
            if (user.isPresent() && status == true) {

                user.get().getListofrole().forEach(role -> {
                    Role role1 = new Role();
                    role1.setRoleName(role.getRoleName());
                    roles.add(role);
                });

                String Token = JwtUtil
                        .generateToken("secret",
                                user.get().getId(),"user",user.get().getName(),roles);
                userRoleDTO.setUserName(user.get().getName());
                userRoleDTO.setId(user.get().getId());
                userRoleDTO.setJwtToken(Token);

            }

        }catch (NoSuchElementException e){
            throw new  ControllerException("401","Unauthorized access!!! ");
        }
        return userRoleDTO;
    }



    public UserDetails loadByUserName(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByName(username);
        List<Role> roles = new LinkedList<>();
        if (userDetail == null) {
            throw new ControllerException("404","User details Not Found..");
        }
        else{

            userDetail.get().getListofrole().forEach(role -> {
                Role role1 = new Role();
                role1.setRoleName(role.getRoleName());
                roles.add(role);
            });

            return new org.springframework.security.core.userdetails
                    .User(userDetail.get().getName(), userDetail.get().getPassword(), getAuthority(roles));
        }
    }


    private List getAuthority(List<Role> role){
        List authorities=new ArrayList();
        role.stream().forEachOrdered(role1 -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" +role1.getRoleName()));
        });
        return authorities;
    }
}
