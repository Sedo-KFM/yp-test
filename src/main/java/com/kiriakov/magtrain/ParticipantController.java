package com.kiriakov.magtrain;

import com.kiriakov.magtrain.entities.*;
import com.kiriakov.magtrain.exceptions.E400;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kiriakov.magtrain.Database.takeNextId;

@Tag(name = "Операции над участниками")
@RestController
@RequestMapping("group/{groupId}/participant")
public class ParticipantController {
    @Operation(summary = "Добавление участника в группу")
    @PostMapping
    public int postParticipant(@PathVariable int groupId,
                               @RequestParam String name,
                               @RequestParam(required = false) String wish) {
        Participant participant = new Participant(name);
        if (wish != null) {
            participant.setWish(wish);
        }
        int id = takeNextId();
        participant.setId(id);
        Database.groups.get(groupId).getParticipants().put(id, participant);
        return id;
    }

    @Operation(summary = "Удаление участника")
    @DeleteMapping("{participantId}")
    public void deleteParticipant(@PathVariable int groupId,
                                  @PathVariable int participantId) {
        Participant removingParticipant = Database.groups.get(groupId).getParticipants().get(participantId);
        if (removingParticipant == null) {
            throw new E400();
        }
        Collection<Participant> participants = Database.groups.get(groupId).getParticipants().values();
        participants.remove(removingParticipant);
        for (Participant participant : participants) {
            if (participant.getRecipient() == removingParticipant) {
                participant.setRecipient(null);
            }
        }
        Database.groups.get(groupId).getParticipants().remove(participantId);
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

    @Operation(summary = "Получение информации о подопечном участника")
    @GetMapping("{participantId}/recipient")
    public SimpleParticipant getGroup(@PathVariable int groupId,
                                      @PathVariable int participantId) {
        return new SimpleParticipant(Database.groups.get(groupId).getParticipants().get(participantId).getRecipient());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
