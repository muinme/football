package com.example.football.services.Impl;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.User;
import com.example.football.repositories.DetailPitchRepository;
import com.example.football.repositories.UserRepository;
import com.example.football.services.MailService;
import com.example.football.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Value("${app.host}")
    private String host;

    @Value("${app.hostWeb}")
    private String hostWeb;

    @Value("${app.upload_folder}")
    private String upload_folder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailPitchRepository detailPitchRepository;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus("Active");
        user.setImage("/images/default-user.png");
        user.setGroup_id(2);
        user.setCreated(new Date());
        return userRepository.save(user);
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByPitchId(String id) {
        return userRepository.findByPitchId(id);
    }

    @Override
    public User findByFootBallId(String id) {
        return userRepository.findByFootBallId(id);
    }

    @Override
    public String loginUser(UserDetails userDetails, HttpServletResponse httpServletResponse) throws ServletException {
        String status = userRepository.findStatusByUserName(userDetails.getUsername());
        System.out.println(status);
        if(status.equals("Deactive"))
        {
            throw new ServletException("Error Accout");
        }
        final String token = jwtUtil.generateToken(userDetails);
        cookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, host);
        return token;
    }

    @Override
    public String logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        jwtUtil.invalidateRelatedTokens(httpServletRequest);
        cookieUtil.getValue(httpServletRequest, "JWT-TOKEN");
        cookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "logout....";
    }

    @Override
    public User getByIdUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateProfileUser(User user, String username) {
        User existingUser = userRepository.findByUsername(username);
        existingUser.setFullname(user.getFullname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        return userRepository.save(existingUser);
    }

    @Override
    public User updatePassWordUser(User user, String username) {
        User existingUser = userRepository.findByUsername(username);
        existingUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(existingUser);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listAllUserMember() {

        return userRepository.findAllUserMember();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public User updateStatusUser1(Integer id) {
        User  existingUser = userRepository.findById(id).get();
        existingUser.setStatus("Active");
        return userRepository.save(existingUser);
    }

    @Override
    public User updateStatusUser2(Integer id) {
        User  existingUser = userRepository.findById(id).get();
        existingUser.setStatus("Deactive");
        return userRepository.save(existingUser);
    }

    @Override
    public User resetAccount(String username) {
        User user = userRepository.findByUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        mailService.sendEmailResetAccount(username);
        userRepository.save(user);
        return  userRepository.save(user);
    }

    @Override
    public User updateLoadAvatar(String url, String username) {
        User existingUser = userRepository.findByUsername(username);
        existingUser.setImage(getString(url));
        return userRepository.save(existingUser);
    }

    public String getString(String m)
    {
        String result = hostWeb +  m.substring(upload_folder.length(),m.length());
            return result;
    }
}
