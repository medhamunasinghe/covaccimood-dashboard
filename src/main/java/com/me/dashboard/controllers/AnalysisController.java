package com.me.dashboard.controllers;

import com.me.dashboard.services.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ComponentScan
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/csa")
public class AnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);

    @Autowired
    AnalysisService analysisService;

    @PostMapping(value = "analyse", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> analyse(@RequestBody(required = false) String payload) {
        try {
            System.out.println("predicting in controller...");
            return new ResponseEntity<>(analysisService.analyse(payload), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "analyseonlysentiment", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> analyseonlysentiment(@RequestBody(required = false) String payload) {
        try {
            String inputJson = analysisService.analyse(payload);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(inputJson).getAsJsonObject();
            JsonObject sentimentObject = new JsonObject();
            sentimentObject.addProperty("sentiment", jsonObject.get("sentiment").getAsString());
            return new ResponseEntity<>(String.valueOf(sentimentObject), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
