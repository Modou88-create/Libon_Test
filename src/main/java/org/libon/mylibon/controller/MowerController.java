package org.libon.mylibon.controller;

import org.libon.mylibon.model.LibonRequest;
import org.libon.mylibon.model.MowerPositionResponse;
import org.libon.mylibon.service.MowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("libon/api")
public class MowerController {


    private final MowerService mowerService;

    public MowerController(MowerService mowerService) {
        this.mowerService = mowerService;
    }

    @PostMapping(value = "/mowers",consumes="application/json")

    public ResponseEntity<List<MowerPositionResponse>>getMowersPositions(@RequestBody LibonRequest libonRequest){
        List<MowerPositionResponse> theMowersPositions = mowerService.getTheMowersPostions(libonRequest);
        return  ResponseEntity.ok(theMowersPositions) ;
    }


}
