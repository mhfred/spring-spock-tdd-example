package com.example.controller

import com.example.SpringSpockTddExampleApplication
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by deadlock on 27/1/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [SpringSpockTddExampleApplication])
class MeetingRoomControllerFunctionalSpec extends Specification {
    @Autowired
    WebApplicationContext webApplicationContext

    MockMvc mockMvc

    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    def 'GET /meeting_room/1/booking -> status is 200'() {
        when:
        ResultActions resultActions = mockMvc.perform(get('/meeting_room/1/booking'))

        then:
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.status', Matchers.equalToIgnoringCase("success")))
                .andExpect(jsonPath('$.status_code').value(1))
    }

    @Unroll
    'GET /meeting_room/#id/booking -> status is 200'() {
        given:
        def path = "/meeting_room/${id}/booking"
        ResultActions resultActions = mockMvc.perform(get(path))

        expect:
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.status', Matchers.equalToIgnoringCase("success")))
                .andExpect(jsonPath('$.status_code').value(1))

        where:
        id << [1, 2, 3, 4, 100]
    }

    def 'GET /meeting_room/{id}/booking -> response format is correct'() {
        given: 'assume exists data for id = 1'

        when:
        ResultActions resultActions = mockMvc.perform(get('/meeting_room/1/booking'))

        then:
        resultActions
                .andExpect(jsonPath('$.data').isArray())
                .andExpect(jsonPath('$.data[0]', Matchers.hasKey("id")))
                .andExpect(jsonPath('$.data[0]', Matchers.hasKey("date")))
                .andExpect(jsonPath('$.data[0]', Matchers.hasKey("reserved_slots")))
    }

    def 'GET /meeting_room/2/booking -> response format is correct'() {
        given: ''

        when:
        ResultActions resultActions = mockMvc.perform(get('/meeting_room/2/booking'))

        then:
        resultActions
                .andExpect(jsonPath('$.data').isArray())
                .andExpect(jsonPath('$.data[0]', Matchers.hasKey("id")))
                .andExpect(jsonPath('$.data[0]', Matchers.hasKey("date")))
                .andExpect(jsonPath('$.data[0]', Matchers.hasKey("reserved_slots")))
    }


}