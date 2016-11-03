package shcases.web.rest;

import shcases.ShowCasesApp;
import shcases.domain.Grodek;
import shcases.repository.GrodekRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GrodekResource REST controller.
 *
 * @see GrodekResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShowCasesApp.class)
public class GrodekResourceIntTest {
    private static final String DEFAULT_PLACE = "AAAAA";
    private static final String UPDATED_PLACE = "BBBBB";
    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_COORDS = "AAAAA";
    private static final String UPDATED_COORDS = "BBBBB";
    private static final String DEFAULT_SIGN_TYPE = "AAAAA";
    private static final String UPDATED_SIGN_TYPE = "BBBBB";
    private static final String DEFAULT_PHOTO = "AAAAA";
    private static final String UPDATED_PHOTO = "BBBBB";

    @Inject
    private GrodekRepository grodekRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restGrodekMockMvc;

    private Grodek grodek;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GrodekResource grodekResource = new GrodekResource();
        ReflectionTestUtils.setField(grodekResource, "grodekRepository", grodekRepository);
        this.restGrodekMockMvc = MockMvcBuilders.standaloneSetup(grodekResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Grodek createEntity() {
        Grodek grodek = new Grodek();
        grodek = new Grodek()
                .place(DEFAULT_PLACE)
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .coords(DEFAULT_COORDS)
                .sign_type(DEFAULT_SIGN_TYPE)
                .photo(DEFAULT_PHOTO);
        return grodek;
    }

    @Before
    public void initTest() {
        grodekRepository.deleteAll();
        grodek = createEntity();
    }

    @Test
    public void createGrodek() throws Exception {
        int databaseSizeBeforeCreate = grodekRepository.findAll().size();

        // Create the Grodek

        restGrodekMockMvc.perform(post("/api/grodeks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(grodek)))
                .andExpect(status().isCreated());

        // Validate the Grodek in the database
        List<Grodek> grodeks = grodekRepository.findAll();
        assertThat(grodeks).hasSize(databaseSizeBeforeCreate + 1);
        Grodek testGrodek = grodeks.get(grodeks.size() - 1);
        assertThat(testGrodek.getPlace()).isEqualTo(DEFAULT_PLACE);
        assertThat(testGrodek.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGrodek.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGrodek.getCoords()).isEqualTo(DEFAULT_COORDS);
        assertThat(testGrodek.getSign_type()).isEqualTo(DEFAULT_SIGN_TYPE);
        assertThat(testGrodek.getPhoto()).isEqualTo(DEFAULT_PHOTO);
    }

    @Test
    public void getAllGrodeks() throws Exception {
        // Initialize the database
        grodekRepository.save(grodek);

        // Get all the grodeks
        restGrodekMockMvc.perform(get("/api/grodeks?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(grodek.getId())))
                .andExpect(jsonPath("$.[*].place").value(hasItem(DEFAULT_PLACE.toString())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].coords").value(hasItem(DEFAULT_COORDS.toString())))
                .andExpect(jsonPath("$.[*].sign_type").value(hasItem(DEFAULT_SIGN_TYPE.toString())))
                .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())));
    }

    @Test
    public void getGrodek() throws Exception {
        // Initialize the database
        grodekRepository.save(grodek);

        // Get the grodek
        restGrodekMockMvc.perform(get("/api/grodeks/{id}", grodek.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grodek.getId()))
            .andExpect(jsonPath("$.place").value(DEFAULT_PLACE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.coords").value(DEFAULT_COORDS.toString()))
            .andExpect(jsonPath("$.sign_type").value(DEFAULT_SIGN_TYPE.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()));
    }

    @Test
    public void getNonExistingGrodek() throws Exception {
        // Get the grodek
        restGrodekMockMvc.perform(get("/api/grodeks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateGrodek() throws Exception {
        // Initialize the database
        grodekRepository.save(grodek);
        int databaseSizeBeforeUpdate = grodekRepository.findAll().size();

        // Update the grodek
        Grodek updatedGrodek = grodekRepository.findOne(grodek.getId());
        updatedGrodek
                .place(UPDATED_PLACE)
                .name(UPDATED_NAME)
                .description(UPDATED_DESCRIPTION)
                .coords(UPDATED_COORDS)
                .sign_type(UPDATED_SIGN_TYPE)
                .photo(UPDATED_PHOTO);

        restGrodekMockMvc.perform(put("/api/grodeks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedGrodek)))
                .andExpect(status().isOk());

        // Validate the Grodek in the database
        List<Grodek> grodeks = grodekRepository.findAll();
        assertThat(grodeks).hasSize(databaseSizeBeforeUpdate);
        Grodek testGrodek = grodeks.get(grodeks.size() - 1);
        assertThat(testGrodek.getPlace()).isEqualTo(UPDATED_PLACE);
        assertThat(testGrodek.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGrodek.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGrodek.getCoords()).isEqualTo(UPDATED_COORDS);
        assertThat(testGrodek.getSign_type()).isEqualTo(UPDATED_SIGN_TYPE);
        assertThat(testGrodek.getPhoto()).isEqualTo(UPDATED_PHOTO);
    }

    @Test
    public void deleteGrodek() throws Exception {
        // Initialize the database
        grodekRepository.save(grodek);
        int databaseSizeBeforeDelete = grodekRepository.findAll().size();

        // Get the grodek
        restGrodekMockMvc.perform(delete("/api/grodeks/{id}", grodek.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Grodek> grodeks = grodekRepository.findAll();
        assertThat(grodeks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
