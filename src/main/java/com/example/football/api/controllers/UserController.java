package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.JwtRequest;
import com.example.football.models.JwtResponse;
import com.example.football.models.User;
import com.example.football.services.AuthenticationService;
import com.example.football.services.Impl.UserServiceImpl;
import com.example.football.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/football")
public class UserController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        return userService.createUser(user);
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse httpServletResponse) throws Exception {
        authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(userService.loginUser(userDetails, httpServletResponse)));
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)

    public ResponseEntity<?> deleteAuthenticationToken(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        httpServletRequest.setAttribute("username", username);
        return ResponseEntity.ok(new JwtResponse(userService.logoutUser(httpServletRequest, httpServletResponse)));
    }

    @RequestMapping(value = {"/user/getAllUserMember"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserMember() throws Exception {

        return new ResponseEntity<>(userService.listAllUserMember(), HttpStatus.OK);
    }

    @RequestMapping(value = {"/userById/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getByIdUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/user/{username}"}, method = RequestMethod.GET)
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/userByPitchId/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<User> getByPitchIdId(@PathVariable String id) {
        try {
            User user = userService.findByPitchId(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/userByFootBallId/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<User> getByFootBallId(@PathVariable String id) {
        try {
            User user = userService.findByFootBallId(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/userByUsername"}, method = RequestMethod.GET)
    public ResponseEntity<User> getUsername(HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        try {
            User user = userService.findByUsername(username);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/userUsernameRequestMatch"}, method = RequestMethod.GET)
    public ResponseEntity<User> getUsernameRequestMatch(HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        try {
            User user = userService.findByUsername(username);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/user/update/{id}"}, method = RequestMethod.POST)
    public ResponseEntity<?> updateById(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existUser = userService.getByIdUser(id);
            try {
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception internalError) {
                internalError.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/user/updatePassWordByUsername"}, method = RequestMethod.POST)
    public ResponseEntity<User> updatePassWordByUsername(@RequestBody User user, HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        System.out.println(jwt);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        try {
            userService.updatePassWordUser(user, username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception internalError) {
            internalError.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/user/updateLoadAvatarByUsername/{url}"}, method = RequestMethod.POST)
    public ResponseEntity<User> updateLoadAvatarByUsername(@PathVariable String url, HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        System.out.println(jwt);
        if (null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        try {
            userService.updateLoadAvatar(url, username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception internalError) {
            internalError.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/user/updateProfileByUsername/{username}"}, method = RequestMethod.POST)
    public ResponseEntity<?> updateProfileByUsername(@RequestBody User user, @PathVariable String username) {
            try {
                return new ResponseEntity<>(userService.updateProfileUser(user, username),HttpStatus.OK);
            } catch (Exception internalError) {
                internalError.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @RequestMapping(value = {"/user/delete/{id}"}, method = RequestMethod.POST)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            User existUser = userService.getByIdUser(id);
            userService.deleteUser(existUser.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/user/delete/{username}"}, method = RequestMethod.POST)
    public ResponseEntity<?> deleteByUsername(@RequestBody User user, @PathVariable String username) {
        try {
            User existUser = userService.findByUsername(username);
            userService.deleteUser(existUser.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/user/updateStatusById1/{id}"}, method = RequestMethod.POST)
    public User updateStatusUser1(@PathVariable Integer id) {
        System.out.println("lll");
        return userService.updateStatusUser1(id);
    }
    @RequestMapping(value = {"/user/updateStatusById2/{id}"}, method = RequestMethod.POST)
    public User updateStatusUser2(@PathVariable Integer id) {
        System.out.println("lll2");
        return userService.updateStatusUser2(id);
    }

}
