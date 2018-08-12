package org.mashup.takoyaki;

import org.junit.runner.RunWith;
import org.mashup.takoyaki.configs.RootContextConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootContextConfig.class })
@WebAppConfiguration
public class SpringTestSupport {

}