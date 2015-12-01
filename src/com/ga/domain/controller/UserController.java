package com.ga.domain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.common.JsonUtility;
import com.ga.domain.model.UserDTO;
import com.ga.exception.ErrorCodes;
import com.ga.exception.GAException;
import com.ga.repository.IUserService;

/**
 * The Class UserController.
 *
 * @author Smit
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /** The user service. */
    @Autowired
    IUserService userService;

    /**
     * User login.
     *
     * @param username the username
     * @param password the password
     * @return the string
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        LOGGER.info("User login called!!");

        try {
            if (username.isEmpty() && password.isEmpty()) {
                throw new GAException(ErrorCodes.GA_MANDATORY_PARAMETERS_NOT_SET);
            }

            UserDTO userDto = userService.userLogin(username, password);
            LOGGER.info("User login complete!!");
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, userDto);
        } catch (GAException e) {
            e.printStackTrace();
            if (e.getCode() == ErrorCodes.GA_AUTH_FAILED.getErrorCode()) {
                return JsonUtility.getJson(ErrorCodes.GA_AUTH_FAILED);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND);
            }
        }
    }
}