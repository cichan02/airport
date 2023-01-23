package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.TicketService;
import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.dto.TicketDTO;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import by.piskunou.solvdlaba.web.mapper.TicketMapper;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;
    private final TicketService ticketService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> findAll() {
        return userMapper.toDTO(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable long id) {
        User user = userService.findById(id);
        return userMapper.toDTO(user);
    }

    @GetMapping("/{id}/tickets")
    public UserDTO findUserTickets(@PathVariable long id) {
        User user = userService.findUserTickets(id);
        return userMapper.toDTO(user);
    }

    @PreAuthorize("hasOwner(#id, #ticket_id)")
    @GetMapping("/{id}/{ticket_id}")
    public TicketDTO findUserTicket(@PathVariable("id") long userId, @PathVariable("ticket_id") long ticketId) {
        Ticket ticket = ticketService.findById(ticketId);

        return ticketMapper.toDTO(ticket);
    }

    @PutMapping("{id}")
    public UserDTO updateUsernameById(@PathVariable long id, @RequestParam String username) {
        User user = userService.updateUsernameById(id, username);
        return userMapper.toDTO(user);
    }

    @PutMapping("{id}/buy_ticket")
    public UserDTO buyTicket(@PathVariable long id, @RequestBody @Validated(onUpdate.class) TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);

        User user = userService.buyTicket(id, ticket);
        return userMapper.toDTO(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable int id) {
        userService.removeById(id);
    }

}
