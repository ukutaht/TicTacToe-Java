package com.heruku.tictactoe.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/TicTacToeWeb-servlet.xml"})
public class ApplicationBootstrapTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testLoad() {
        assertNotNull(applicationContext.getBeanDefinitionNames());
    }
}
