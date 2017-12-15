package app.layerGraphicAndPresentation.controller;

import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.Election;
import app.layerLogicAndService.cmpService.service.IFromHeroService;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Diese Klasse repräsentiert den heroService Endpoint unseres fromHero-heroService
 *
 * @author Chris on 01.12.2017
 */
@RestController
public class HeroController {

    Gson gson = new Gson();

    @Autowired
    IFromHeroService heroService;


    @RequestMapping(
            value = API.PATH_SERVICES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices() {

        try{
            Service service = this.heroService.getService();
            return new ResponseEntity<>(this.gson.toJson(service), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(this.gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(
            value = API.PATH_ELECTION,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addElection(@RequestBody Election request){
        // uri to which one sends addElection messages to>" RequestBody hier ok?

        try{
            this.heroService.addElection(request);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(
            value = API.PATH_MUTEX,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMutex(@RequestBody Mutex request){
        // uri to endpoint where one posts mutex algorithm messages>" - RequestBody hier ok?

        try{

            // TODO - Serviceaufruf
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(
            value = API.PATH_MUTEXSTATE,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMutexstate(@RequestBody Mutex request){
        // uri to endpoint telling the mutex state> ist hier get ok?

        try{

            // TODO - Serviceaufruf
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }



}
