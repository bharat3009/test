package com.ga.repository;

import com.ga.domain.model.UserDTO;
import com.ga.exception.GAException;

/**
 * The Interface IUserService.
 *
 * @author Smit
 */
public interface IUserService {

    /**
     * User login.
     *
     * @param username the username
     * @param password the password
     * @return the user dto
     * @throws GAException the GA exception
     */
    UserDTO userLogin(String username, String password) throws GAException;

}
