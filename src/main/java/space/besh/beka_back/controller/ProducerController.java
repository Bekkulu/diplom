package space.besh.beka_back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.besh.beka_back.entity.Producer;
import space.besh.beka_back.model.Response;
import space.besh.beka_back.repos.ProducerRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProducerController {
    final ProducerRepository producerRepository;

    @GetMapping(produces = "application/json")
    @Operation(summary = "GET", description = "Получение всех поставщиков, или по названию компании, или по фио, или по названию компании и фио")
    public ResponseEntity<Response> getAll(@RequestParam(required = false, name = "company_name") String companyName,
                                           @RequestParam(required = false, name = "fio") String fio ){
        List<Producer> producer=producerRepository.findAll(companyName,fio);
        return ResponseEntity.ok(new Response(producer));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "POST", description = "Создание поставщика")
    public ResponseEntity<Response> save(@RequestBody Producer producer){
        Producer createdProducer=this.producerRepository.save(producer);
        return ResponseEntity.ok(new Response(createdProducer));
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "PUT", description = "Обновление данных поставщика")
    public ResponseEntity<Response> update(@RequestBody Producer producer){
        Producer createdProducer=this.producerRepository.save(producer);
        return ResponseEntity.ok(new Response(createdProducer));
    }

    @DeleteMapping
    @Operation(summary = "DELETE", description = "Удаления поставщика")
    public ResponseEntity<Response> delete(@RequestBody Producer producer){
        this.producerRepository.delete(producer);
        return ResponseEntity.ok(new Response(true));
    }
}
