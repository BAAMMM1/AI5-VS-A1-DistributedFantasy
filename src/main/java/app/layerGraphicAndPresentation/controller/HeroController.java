package app.layerGraphicAndPresentation.controller;

import app.layerGraphicAndPresentation.controller.config.PathHeroservice;
import app.layerLogicAndService.cmpHero.entity.Assignment;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerLogicAndService.cmpHero.service.IHeroService;
import app.layerLogicAndService.cmpHeroToHero.service.IHeroToHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Diese Klasse repräsentiert den service Endpoint unseres hero-service
 *
 * @author Chris on 01.12.2017
 */
@RestController
public class HeroController {

    @Autowired
    IHeroService service;


    @RequestMapping(
            value = PathHeroservice.SERVICES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices() {

        try{
            Service service = this.service.getService();
            return new ResponseEntity<Service>(service, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);

        }



    }


    @RequestMapping(
            value = PathHeroservice.HIRINGS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addHiring(@RequestBody Hiring request){
        return null;
    }


    @RequestMapping(
            value = PathHeroservice.ASSIGNMENTS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignment(@RequestBody Assignment request){
        return null;
    }


    @RequestMapping(
            value = PathHeroservice.MESSAGES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMessage(@RequestBody Message request){
        return null;
    }

}
