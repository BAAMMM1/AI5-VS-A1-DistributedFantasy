package app.layerGraphicAndPresentation.controller;

import app.layerGraphicAndPresentation.controller.config.PathHeroservice;
import app.layerLogicAndService.cmpHero.entity.Assignment;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerLogicAndService.cmpHero.service.IHeroService;
import app.layerLogicAndService.cmpHero.service.exception.AlreadyInGroupException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Diese Klasse repräsentiert den heroService Endpoint unseres hero-heroService
 *
 * @author Chris on 01.12.2017
 */
@RestController
public class HeroController {

    Gson gson = new Gson();

    @Autowired
    IHeroService heroService;


    @RequestMapping(
            value = PathHeroservice.SERVICES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices() {

        try{
            Service service = this.heroService.getService();
            return new ResponseEntity<Service>(service, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);

        }



    }



    @RequestMapping(
            value = "/hirings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addhiring(@RequestBody Hiring request) {

        try {
            System.out.println(request.toString());
            this.heroService.addHiring(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (AlreadyInGroupException e) {
            return new ResponseEntity<>(this.gson.toJson(e.getError()), HttpStatus.BAD_REQUEST);

        }
    }




    @RequestMapping(
            value = PathHeroservice.ASSIGNMENTS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignment(@RequestBody Assignment request){

        try{
            this.heroService.addAssignment(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }


    @RequestMapping(
            value = PathHeroservice.MESSAGES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMessage(@RequestBody Message request){

        try{
            this.heroService.addMessage(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

}
