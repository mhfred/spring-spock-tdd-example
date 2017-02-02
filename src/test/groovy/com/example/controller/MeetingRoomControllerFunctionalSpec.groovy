package com.example.controller

import com.example.SpringSpockTddExampleApplication
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by deadlock on 27/1/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [SpringSpockTddExampleApplication])
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MeetingRoomControllerFunctionalSpec extends Specification {
    @Autowired
    WebApplicationContext webApplicationContext
    @Autowired
    ObjectMapper mapper

    MockMvc mockMvc

    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    def 'GET /meeting_room/booking -> status is 200'() {
        when:
        ResultActions resultActions = mockMvc.perform(get('/meeting_room/booking'))

        then:
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.status', Matchers.equalToIgnoringCase("success")))
                .andExpect(jsonPath('$.status_code').value(1))
    }

    def 'POST /meeting_room/booking -> response is 200 and status is success'() {
        when:
        ResultActions resultActions = mockMvc.perform(post('/meeting_room/booking')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content('{"begin":"2017-02-01T13:00:00.000+0800","end":"2017-02-01T14:00:00.000+0800"}')
        )
        then:
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.status').value("success"))
                .andExpect(jsonPath('$.status_code').value(1))
    }

    def 'GET /meeting_room/booking -> response format is correct'() {
        given: 'flyway configuration data: id = 1, name = meeting room'

        when:
        ResultActions resultActions = mockMvc.perform(get('/meeting_room/booking'))

        then:
        resultActions
                .andExpect(jsonPath('$.data').isArray())
    }

    def 'Can query booking info after success booking'() {
        given:
        def requestString = '{"begin":"2017-01-31T11:00:00.000+0800","end":"2017-01-31T12:00:00.000+0800"}'

        when:
        mockMvc.perform(post('/meeting_room/booking').contentType(MediaType.APPLICATION_JSON).content(requestString))
                .andExpect(status().isOk())
        ResultActions resultActions = mockMvc.perform(get('/meeting_room/booking'))

        then:
        resultActions
                .andExpect(jsonPath('$.data').isArray())
                .andExpect(jsonPath('$.data[0].begin').value("2017-01-31T11:00:00.000+0800"))
                .andExpect(jsonPath('$.data[0].end').value("2017-01-31T12:00:00.000+0800"))
    }

}