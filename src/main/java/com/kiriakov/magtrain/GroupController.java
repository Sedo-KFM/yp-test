package com.kiriakov.magtrain;

import com.kiriakov.magtrain.entities.*;
import com.kiriakov.magtrain.exceptions.E400;
import com.kiriakov.magtrain.exceptions.E409;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.kiriakov.magtrain.Database.takeNextId;

@Tag(name = "Операции над группами")
@RestController
@RequestMapping("group")
public class GroupController {
    @Operation(summary = "Добавление группы")
    @PostMapping
    public int postGroup(@RequestParam String name,

                         @RequestParam(required = false) String description) {
        Group group = new Group(name);
        if (description != null) {
            group.setDescription(description);
        }
        int id = takeNextId();
        group.setId(id);
        Database.groups.put(id, group);
        return id;
    }

    @Operation(summary = "Получение краткой информации обо всех групах")
    @GetMapping
    public Set<SimpleGroup> getSome() {
        return Database
                .groups
                .values()
                .stream()
                .map(SimpleGroup::new)
                .collect(Collectors.toSet());
    }

    @Operation(summary = "Получение полной информации о группе по идентификатору")
    @GetMapping("{id}")
    public GroupWithPSPs getGroup(@PathVariable int id) {
        return new GroupWithPSPs(Database.groups.get(id));
    }

    @Operation(summary = "Редактирование группы по её идентификатору")
    @PutMapping("{id}")
    public void putGroup(@PathVariable int id,
                         @RequestParam String name,
                         @RequestParam(required = false) String description) {
        Group group = Database.groups.get(id);
        group.setName(name);
        group.setDescription(description);
    }

    @Operation(summary = "Удаление группы по её идентификатору")
    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable int id) {
        if (Database.groups.remove(id) == null) {
            throw new E400();
        }
    }

    @Operation(summary = "Жеребьевка")
    @PostMapping("{id}/toss")
    public Set<PreSimpleParticipant> toss(@PathVariable int id) {
        Group group = Database.groups.get(id);
        List<Participant> participants = group.getParticipants().values().stream().toList();
        if (participants.size() < 3) {
            throw new E409();
        }
        for (int i = 0; i < participants.size() - 1; i++) {
            participants.get(i).setRecipient(participants.get(i + 1));
        }
        participants.get(participants.size() - 1).setRecipient(participants.get(0));
        return participants
                .stream()
                .map(PreSimpleParticipant::new)
                .collect(Collectors.toSet());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        if (ex.getClass() == E409.class) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
