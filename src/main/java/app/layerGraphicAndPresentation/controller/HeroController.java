package app.layerGraphicAndPresentation.controller;

import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.Election;
import app.layerLogicAndService.cmpService.service.IFromHeroService;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Diese Klasse repräsentiert den fromHeroService Endpoint unseres fromHero-fromHeroService
 *
 * @author Chris on 01.12.2017
 */
@RestController
public class HeroController {

    public final static Logger logger = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());

    Gson gson = new Gson();

    @Autowired
    IFromHeroService fromHeroService;


    @RequestMapping(
            value = API.PATH_SERVICES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices() {

        try{
            Service service = this.fromHeroService.getService();
            return new ResponseEntity<>(this.gson.toJson(service), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }



    }



    @RequestMapping(
            value = API.PATH_HIRINGS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addhiring(@RequestBody Hiring request) {

        try {
            logger.info(request.toString());

            this.fromHeroService.addHiring(request);
            return new ResponseEntity<>("i accepted your hiring!", HttpStatus.OK);

        } catch (AlreadyInGroupException e) {
            return new ResponseEntity<>(new JSONObject().put("message", e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put("message", API.MESSAGE).toString(), HttpStatus.BAD_REQUEST);

        }
    }




    @RequestMapping(
            value = API.PATH_ASSIGNMENTS,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignment(@RequestBody Assignment request){

        try{
            this.fromHeroService.addAssignment(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping(
            value = API.PATH_ASSIGNMENTS + "/deliveries",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignmentDeliver(@RequestBody AssignmentDeliver request){

        try{

            this.fromHeroService.addAssignmentDeliver(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }

    }


    @RequestMapping(
            value = API.PATH_MESSAGES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMessage(@RequestBody Message request){

        try{
            this.fromHeroService.addMessage(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(
            value = API.PATH_ELECTION,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addElection(@RequestBody Election request){
        // uri to which one sends addElection messages to>" RequestBody hier ok?

        logger.info("incoming election: " + request.toString());

        if(Blackboard.getInstance().getUser().isDead()){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        try{
            this.fromHeroService.addElection(request);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }
    }



}
