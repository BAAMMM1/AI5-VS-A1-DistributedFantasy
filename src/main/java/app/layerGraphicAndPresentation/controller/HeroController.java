package app.layerGraphicAndPresentation.controller;

import app.configuration.API;
import app.layerLogicAndService.cmpService.service.hero.IHeroService;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.exception.ErrorMessage;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
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
            value = API.PATH_SERVICES,
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
            value = API.PATH_HIRINGS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addhiring(@RequestBody Hiring request) {

        try {
            System.out.println(request.toString());
            this.heroService.addHiring(request);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (AlreadyInGroupException e) {
            return new ResponseEntity<>(this.gson.toJson(e.getError()), HttpStatus.BAD_REQUEST);

        } catch (UnexpectedResponseCodeException e) {
            return new ResponseEntity<>(this.gson.toJson(new ErrorMessage(e.getMessage())), HttpStatus.BAD_REQUEST);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(this.gson.toJson(new ErrorMessage(e.getMessage())), HttpStatus.BAD_REQUEST);
        }
    }




    @RequestMapping(
            value = API.PATH_ASSIGNMENTS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignment(@RequestBody Assignment request){

        try{
            this.heroService.addAssignment(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping(
            value = API.PATH_ASSIGNMENTS + "/deliveries",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignmentDeliver(@RequestBody AssignmentDerliver request){

        try{

            this.heroService.addAssignmentDeliver(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }


    @RequestMapping(
            value = API.PATH_MESSAGES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMessage(@RequestBody Message request){

        try{
            this.heroService.addMessage(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

}
