package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.UserRepository;
import by.piskunou.solvdlaba.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void verifyFindAllTest() {
        service.findAll();
        verify(repository).findAll();
    }

    @Test
    void verifyFindByIdTest() {
        User user = User.builder()
                        .id(1L)
                        .username("Cool boy333")
                        .role(User.Role.USER)
                        .build();
        when(repository.findById(1)).thenReturn(Optional.of(user));

        assertEquals(user, service.findById(1, false));
        verify(repository).findById(1);
    }

    @Test
    void verifyFindByIdWithTicketsTest() {
        List<Ticket> tickets = new LinkedList<>();
        User user = User.builder()
                        .id(1L)
                        .username("Cool boy333")
                        .role(User.Role.USER)
                        .tickets(tickets)
                        .build();
        when(repository.findByIdWithTickets(1)).thenReturn(Optional.of(user));

        assertEquals(user, service.findById(1, true));
        verify(repository).findByIdWithTickets(1);
    }

    @Test
    void verifyFindByIdFailedTest() {
        assertThrows(ResourceNotExistsException.class, () -> service.findById(Long.MAX_VALUE, false));
        verify(repository).findById(Long.MAX_VALUE);
    }

    @Test
    void verifyFindByUsernameTest() {
        User user = User.builder()
                        .id(1L)
                        .username("Cool boy333")
                        .password("MTIzNA==")
                        .role(User.Role.USER)
                        .build();
        when(repository.findByUsername("Cool boy333")).thenReturn(Optional.of(user));

        assertEquals(user, service.findByUsername("Cool boy333"));
        verify(repository).findByUsername("Cool boy333");
    }


    @Test
    void verifyFindByUsernameFailedTest() {
        assertThrows(ResourceNotExistsException.class, () -> service.findByUsername("Cool boy333"));
        verify(repository).findByUsername("Cool boy333");
    }

    @Test
    void verifySearchTest() {
        User user = User.builder()
                        .username("Oks")
                        .build();

        service.search(user);
        assertEquals("%Oks%", user.getUsername());
        verify(repository).search(user);
    }

    @Test
    void verifyNullSearchTest() {
        User user = new User();

        service.search(user);
        assertEquals("%%", user.getUsername());
        verify(repository).search(user);
    }

    @Test
    void verifyCreateTest() {
        User user = User.builder()
                        .username("Cool boy333")
                        .email("lala@gmail.com")
                        .password("MTIzNA==")
                        .build();
        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                User user = invocation.getArgument(0);
                user.setId(1L);
                return null;
            }
        }).when(repository).create(user);

        assertEquals(user, service.create(user));
        verify(encoder).encode("MTIzNA==");
        assertEquals(User.Role.USER, user.getRole());
        verify(repository).isExistsByUsername(null, "Cool boy333");
        verify(repository).create(user);
        assertEquals(1, user.getId());
    }

    @Test
    void verifyCreateExistedNameTest() {
        User user = User.builder()
                        .username("Cool boy333")
                        .email("lala@gmail.com")
                        .password("MTIzNA==")
                        .build();
        when(service.isExistsByUsername(null, "Cool boy333")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> service.create(user));
        verify(encoder).encode("MTIzNA==");
        assertEquals(User.Role.USER, user.getRole());
        verify(repository).isExistsByUsername(null, "Cool boy333");
    }

    @Test
    void verifyCreateExistedEmailTest() {
        User user = User.builder()
                        .username("Cool boy333")
                        .email("lala@gmail.com")
                        .password("MTIzNA==")
                        .build();
        when(service.isExistsByEmail(null, "lala@gmail.com")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> service.create(user));
        verify(encoder).encode("MTIzNA==");
        assertEquals(User.Role.USER, user.getRole());
        verify(repository).isExistsByUsername(null, "Cool boy333");
        verify(repository).isExistsByEmail(null, "lala@gmail.com");
    }

    @Test
    void verifyUpdateByIdTest() {
        User user = User.builder()
                        .username("Cool boy333")
                        .email("lala@gmail.com")
                        .role(User.Role.USER)
                        .build();
        when(service.isExists(1)).thenReturn(true);

        assertEquals(user, service.updateById(1, user));
        verify(repository).isExistsById(1);
        assertEquals(1, user.getId());
        verify(repository).update(user);
    }

    @Test
    void verifyUpdateByNonexistentIdTest() {
        assertThrows(ResourceNotExistsException.class, () -> service.updateById(Long.MAX_VALUE, any()));
        verify(repository).isExistsById(Long.MAX_VALUE);
    }

    @Test
    void verifyRemoveByIdTest() {
        service.removeById(1);
        verify(repository).removeById(1);
    }

    @Test
    void verifyIsExistsTest() {
        assertFalse(service.isExists(1));
        verify(repository).isExistsById(1);
    }

    @Test
    void verifyIsExistsByUsernameTest() {
        assertFalse(service.isExistsByUsername(0L, "John"));
        verify(repository).isExistsByUsername(0L, "John");
    }

}