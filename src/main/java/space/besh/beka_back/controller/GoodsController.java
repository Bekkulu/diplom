package space.besh.beka_back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.besh.beka_back.entity.*;
import space.besh.beka_back.enums.Status;
import space.besh.beka_back.model.Response;
import space.besh.beka_back.repos.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    private final GoodsRepo goodsRepo;
    private final ProducerRepository producerRepository;
    private final GoodsInRepository goodsInRepository;
    private final ConsumerRepository consumerRepository;
    private final GoodsOutRepository goodsOutRepository;

    public GoodsController(GoodsRepo goodsRepo, ProducerRepository producerRepository, GoodsInRepository goodsInRepository, ConsumerRepository consumerRepository, GoodsOutRepository goodsOutRepository) {
        this.goodsRepo = goodsRepo;

        this.producerRepository = producerRepository;
        this.goodsInRepository = goodsInRepository;
        this.consumerRepository = consumerRepository;
        this.goodsOutRepository = goodsOutRepository;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "GET", description = "Получение всех продуктов, или по типу продукта, или по названию продукта, или по названию продукта и типу продукта")
    public ResponseEntity<Response> getAllGoods(@RequestParam(required = false, name = "type") Long typeId, @RequestParam(required = false) String name) {
        List<Goods> goods = goodsRepo.findAll(typeId,name);
        log.info("getAllGoods > returned {}", goods);
        return ResponseEntity.ok(buildSuccessResponse(goods, null));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "GET", description = "Получение продукта по идентификатору")
    public ResponseEntity<Response> getGoods(@PathVariable long id) {
        Optional<Goods> goodsById = goodsRepo.findById(id);
        if (goodsById.isPresent()) {
            log.info("getGoods > returned {}", goodsById);
            return ResponseEntity.ok(buildSuccessResponse(goodsById, null));
        } else {
            log.info("getGoods > returned empty");
            return ResponseEntity.ok(
                    new Response()
                            .setStatus(Status.FAIL)
                            .setMessage("Could not find goods with id " + id));
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "POST", description = "Создание продукта")
    public ResponseEntity<Response> addGoods(@RequestBody Goods goods, @RequestParam(name = "producer_id") Long producerId) {
        try {
            if (goods.getId() != null) {
                return ResponseEntity.ok(
                        new Response()
                                .setStatus(Status.FAIL)
                                .setMessage("The field Id is autogenerated")
                );
            }
            goods=goodsRepo.save(goods);

            Producer producer=producerRepository.findById(producerId).get();
            GoodsIn goodsIn=new GoodsIn();
            goodsIn.setName(goods.getName());
            goodsIn.setPrice(goods.getPrice());
            goodsIn.setProducer(producer);
            goodsIn.setQuantityIn(goods.getQuantity());
            goodsIn.setProduct(goods);

            goodsInRepository.save(goodsIn);
        } catch (Exception e) {
            return handleError(e, "Error while saving goods");
        }
        log.info("addGoods > saved {}", goods);
        return ResponseEntity.ok(buildSuccessResponse(goods, "saved entity goods"));
    }

    @RequestMapping(method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
    @Operation(summary = "PATCH", description = "Обновление продукта")
    public ResponseEntity<Response> updateGoods(@RequestBody Goods goods, @RequestParam(name = "member_id") Long memberId) {
        log.info("updateGoods > goods {}", goods);

        Optional<Goods> goodsById = goodsRepo.findById(goods.getId());
        if (goodsById.isPresent()) {
            Goods goodFromDb=goodsById.get();
            if(goodFromDb.getQuantity()<goods.getQuantity()){
                Producer producer=producerRepository.findById(memberId).get();
                GoodsIn goodsIn=new GoodsIn();
                goodsIn.setName(goods.getName());
                goodsIn.setPrice(goods.getPrice());
                goodsIn.setProducer(producer);
                goodsIn.setQuantityIn(goods.getQuantity()-goodFromDb.getQuantity());
                goodsIn.setProduct(goods);
                goodsInRepository.save(goodsIn);
            }else {
                Consumer consumer=consumerRepository.findById(memberId).get();
                GoodsOut goodsOut=new GoodsOut();
                goodsOut.setName(goods.getName());
                goodsOut.setPrice(goods.getPrice());
                goodsOut.setConsumer(consumer);
                goodsOut.setQuantityOut(goodFromDb.getQuantity()-goods.getQuantity());
                goodsOut.setGood(goods);
                goodsOutRepository.save(goodsOut);
            }
            goodsRepo.save(goods);
            log.info("updateGoods > updated entity goods from {} to {} ", goodsById, goods);
            return ResponseEntity.ok(buildSuccessResponse(goods, "updated entity goods"));
        } else {
            return ResponseEntity.ok(
                    new Response()
                            .setStatus(Status.FAIL)
                            .setMessage("Couldn't find goods with id " + goods.getId())
            );
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json")
    @Operation(summary = "GET", description = "Удаление продукта")
    public ResponseEntity<Response> deleteGoods(@PathVariable long id) {
        try {

            log.info("deleteGoods > searching for goods id {}", id);
            Optional<Goods> goodsById = goodsRepo.findById(id);

            if (goodsById.isPresent()) {
                goodsRepo.delete(goodsById.get());
                log.info("deleteGoods > deleted entity goods {}", goodsById.get());
                return ResponseEntity.ok(buildSuccessResponse(goodsById, "deleted entity goods"));
            } else {
                log.info("Couldn't find goods with id {}", id);
                return handleError(new EntityNotFoundException(), "Couldn't find goods with id " + id);
            }

        } catch (Exception e) {
            return handleError(e, "Error while deleting goods");
        }
    }

    @GetMapping(value = "/in", produces = "application/json")
    @Operation(summary = "GET", description = "Получение поступлений продуктов")
    public ResponseEntity<Response> getAllGoodsIn(@RequestParam(name = "good_id",required = false) Long goodId){
        List<GoodsIn> goodsInList=goodsInRepository.findAll(goodId);
        return ResponseEntity.ok(new Response(goodsInList));
    }

    @GetMapping(value = "/out", produces = "application/json")
    @Operation(summary = "GET", description = "Получение выдачи продуктов")
    public ResponseEntity<Response> getAllGoodsOut(@RequestParam(name = "good_id",required = false) Long goodId){
        List<GoodsOut> goodsOutList=goodsOutRepository.findAll(goodId);
        return ResponseEntity.ok(new Response(goodsOutList));
    }

    private Response buildSuccessResponse(Object data, String message) {
        return new Response()
                .setStatus(Status.SUCCESS)
                .setData(data)
                .setMessage(message);
    }

    private ResponseEntity<Response> handleError(Exception e, String message) {
        log.error(message, e);
        return ResponseEntity.ok(
                new Response()
                        .setStatus(Status.FAIL)
                        .setMessage(message));
    }
}
