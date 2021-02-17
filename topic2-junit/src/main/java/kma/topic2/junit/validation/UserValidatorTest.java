package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidator userValidator;


    @Test
    void shouldValidatePassword() {
        userValidator.validateNewUser(
                NewUser.builder()
                        .fullName("Jack")
                        .login("JackMan99")
                        .password("jack99")
                        .build()
        );
        verify(userRepository).isLoginExists("JackMan99");
    }

    @Test
    void shouldThrowException_LoginExists(){
        Mockito.when(userRepository.isLoginExists("JackMan99")).thenReturn(true);

        assertThatThrownBy(()->userValidator.validateNewUser(
                NewUser.builder()
                        .fullName("Jack")
                        .login("JackMan99")
                        .password("jack99")
                        .build()
        )).isInstanceOf(LoginExistsException.class);
    }
}