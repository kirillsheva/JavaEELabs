package kma.topic2.junit.service;


import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @SpyBean
    private UserValidator userValidator;

    @Test
    void shouldCreateNewUser() {
        userService.createNewUser(
                NewUser.builder()
                        .fullName("Jack")
                        .login("JackMan99")
                        .password("jack99")
                        .build()
        );
        /*assertThat(userRepository.getUserByLogin("JackMan99"))
                .isEqualTo(
                        User.builder()
                                .fullName("Jack")
                                .login("JackMan99")
                                .password("jack99")
                                .build()
                );*/
        assertThat(userRepository.getUserByLogin("JackMan99"))
                .returns("JackMan99",User::getLogin)
                .returns("jack99",User::getPassword);


        verify(userValidator).validateNewUser(
                NewUser.builder()
                        .fullName("Jack")
                        .login("JackMan99")
                        .password("jack99")
                        .build()
                );
        // verify(userValidator).validateNewUser(any());
    }
}