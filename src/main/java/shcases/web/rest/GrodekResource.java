package shcases.web.rest;

import com.codahale.metrics.annotation.Timed;
import shcases.domain.Grodek;

import shcases.repository.GrodekRepository;
import shcases.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Grodek.
 */
@RestController
@RequestMapping("/api")
public class GrodekResource {

    private final Logger log = LoggerFactory.getLogger(GrodekResource.class);
        
    @Inject
    private GrodekRepository grodekRepository;

    /**
     * POST  /grodeks : Create a new grodek.
     *
     * @param grodek the grodek to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grodek, or with status 400 (Bad Request) if the grodek has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/grodeks",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Grodek> createGrodek(@RequestBody Grodek grodek) throws URISyntaxException {
        log.debug("REST request to save Grodek : {}", grodek);
        if (grodek.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("grodek", "idexists", "A new grodek cannot already have an ID")).body(null);
        }
        Grodek result = grodekRepository.save(grodek);
        return ResponseEntity.created(new URI("/api/grodeks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("grodek", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grodeks : Updates an existing grodek.
     *
     * @param grodek the grodek to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grodek,
     * or with status 400 (Bad Request) if the grodek is not valid,
     * or with status 500 (Internal Server Error) if the grodek couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/grodeks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Grodek> updateGrodek(@RequestBody Grodek grodek) throws URISyntaxException {
        log.debug("REST request to update Grodek : {}", grodek);
        if (grodek.getId() == null) {
            return createGrodek(grodek);
        }
        Grodek result = grodekRepository.save(grodek);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("grodek", grodek.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grodeks : get all the grodeks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of grodeks in body
     */
    @RequestMapping(value = "/grodeks",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Grodek> getAllGrodeks() {
        log.debug("REST request to get all Grodeks");
        List<Grodek> grodeks = grodekRepository.findAll();
        return grodeks;
    }

    /**
     * GET  /grodeks/:id : get the "id" grodek.
     *
     * @param id the id of the grodek to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grodek, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/grodeks/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Grodek> getGrodek(@PathVariable String id) {
        log.debug("REST request to get Grodek : {}", id);
        Grodek grodek = grodekRepository.findOne(id);
        return Optional.ofNullable(grodek)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /grodeks/:id : delete the "id" grodek.
     *
     * @param id the id of the grodek to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/grodeks/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteGrodek(@PathVariable String id) {
        log.debug("REST request to delete Grodek : {}", id);
        grodekRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("grodek", id.toString())).build();
    }

}
