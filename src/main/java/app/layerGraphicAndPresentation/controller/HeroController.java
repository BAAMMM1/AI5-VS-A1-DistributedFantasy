package app.layerGraphicAndPresentation.controller;

import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.Election;
import app.layerLogicAndService.cmpService.entity.hero.mutex.Mutex;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexRequest;
import app.layerLogicAndService.cmpService.service.IFromHeroService;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.xlayerMiddleware.logicalClock.LamportClock;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Diese Klasse repräsentiert den fromHeroService Endpoint unseres fromHero-fromHeroService
 *
 * @author Chris on 01.12.2017
 */
@RestController
public class HeroController {

    public final static Logger logger = Logger.getLogger(new Object() {
    }.getClass().getEnclosingClass());

    Gson gson = new Gson();

    @Autowired
    IFromHeroService fromHeroService;


    @RequestMapping(
            value = API.PATH_SERVICES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices() {

        try {
            Service service = this.fromHeroService.getService();
            return new ResponseEntity<>(this.gson.toJson(service), HttpStatus.OK);

        } catch (Exception e) {
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
    public ResponseEntity<?> addAssignment(@RequestBody Assignment request) {

        try {
            this.fromHeroService.addAssignment(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping(
            value = API.PATH_ASSIGNMENTS + "/deliveries",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAssignmentDeliver(@RequestBody AssignmentDeliver request) {

        try {

            this.fromHeroService.addAssignmentDeliver(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }

    }


    @RequestMapping(
            value = API.PATH_MESSAGES,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMessage(@RequestBody Message request, HttpServletRequest req) {

        logger.info("form ip: " + req.getRemoteAddr());

        try {
            this.fromHeroService.addMessage(request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(
            value = API.PATH_ELECTION,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addElection(@RequestBody Election request) {
        // uri to which one sends addElection messages to>" RequestBody hier ok?

        logger.info("incoming election: " + request.toString());

        if (Blackboard.getInstance().getUser().isDead()) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        try {
            this.fromHeroService.addElection(request);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(
            value = API.PATH_MUTEX,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMutex(@RequestBody MutexRequest request) {
        // uri to endpoint where one posts mutex algorithm messages>" - RequestBody hier ok?

        logger.info("mutex-request - income: \n" + request.toString());
        LamportClock.getInstance().tick(request.getTime());

        try {
            this.fromHeroService.addMutexRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping(
            value = API.PATH_MUTEX_REPLY + "/" + "{" + API.MUTEX_MESSAGE_ID + "}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMutexReply(@PathVariable(API.MUTEX_MESSAGE_ID) String uuid, @RequestBody MutexRequest request) {
        // uri to endpoint where one posts mutex algorithm messages>" - RequestBody hier ok?

        logger.info("mutex-reply - income from: " + request.getUser() + " with time: " + request.getTime());
        LamportClock.getInstance().tick(request.getTime());

        try {
            this.fromHeroService.addMutexReply(uuid, request);

            return new ResponseEntity<>(HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping(
            value = API.PATH_MUTEXSTATE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMutexstate(HttpServletRequest req) {

        logger.info("mutex-state - income request from: " + req.getRemoteAddr());

        try {

            Mutex currentMutex = this.fromHeroService.getMutexState();
            logger.info("response to: " + req.getRemoteAddr() + " currentMutexState: " + currentMutex.toString());
            return new ResponseEntity<>(gson.toJson(currentMutex), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new JSONObject().put(API.MESSAGE, e.getMessage()).toString(), HttpStatus.BAD_REQUEST);

        }
    }


}
