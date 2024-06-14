package space.besh.beka_back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.besh.beka_back.entity.Consumer;
import space.besh.beka_back.entity.Producer;
import space.besh.beka_back.model.Response;
import space.besh.beka_back.repos.ConsumerRepository;
import space.besh.beka_back.repos.ProducerRepository;

import java.util.List;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsumerController {
    final ConsumerRepository consumerRepository;

    @GetMapping(produces = "application/json")
    @Operation(summary = "GET", description = "Получение всех потребителей, или по фио, или по телефону, или по телефону и фио")
    public ResponseEntity<Response> getAll(@RequestParam(required = false, name = "phone") String phone,
                                           @RequestParam(required = false, name = "fio") String fio ){
        List<Consumer> consumer=consumerRepository.findAll(phone,fio);
        return ResponseEntity.ok(new Response(consumer));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "POST", description = "Создание потребителя")
    public ResponseEntity<Response> save(@RequestBody Consumer consumer){
        Consumer createdConsumer=this.consumerRepository.save(consumer);
        return ResponseEntity.ok(new Response(createdConsumer));
    }


    @Operation(summary = "POST", description = "Обновление данных потребителя")
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> update(@RequestBody Consumer consumer){
        Consumer createdConsumer=this.consumerRepository.save(consumer);
        return ResponseEntity.ok(new Response(createdConsumer));
    }

    @DeleteMapping
    @Operation(summary = "DELETE", description = "Удаления потребителя")
    public ResponseEntity<Response> delete(@RequestBody Consumer consumer){
        this.consumerRepository.delete(consumer);
        return ResponseEntity.ok(new Response(true));
    }
}
